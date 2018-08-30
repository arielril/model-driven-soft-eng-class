package org.plugin.methodcounter.handlers;

import java.util.HashSet;
import java.util.LinkedList;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IMember;
import org.eclipse.jdt.core.IMethod;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.corext.callhierarchy.CallHierarchy;
import org.eclipse.jdt.internal.corext.callhierarchy.MethodWrapper;


@SuppressWarnings("restriction")
public class SampleHandler extends AbstractHandler {

	private LinkedList<IMethod> projectMethods = new LinkedList<>();
	
	public HashSet<IMethod> getCallersOf(IMethod method) {
		CallHierarchy callHc = CallHierarchy.getDefault();
		
		IMember[] members = {method};
		
		MethodWrapper[] methodWrappers = callHc.getCalleeRoots(members);
		HashSet<IMethod> callers = new HashSet<>();
		
		for (MethodWrapper mw : methodWrappers) {
			MethodWrapper[] mw2 = mw.getCalls(new NullProgressMonitor());
			HashSet<IMethod> temp = getIMethods(mw2);
			callers.addAll(temp);
		}
		
		return callers;
	}
	
	private HashSet<IMethod> getIMethods(MethodWrapper[] methodWrappers) {
		HashSet<IMethod> c = new HashSet<>();
		for (MethodWrapper m : methodWrappers) {
			IMethod im = getIMethodFromMethodWrapper(m);
			if (im != null)
				c.add(im);
		}
		
		return c;
	}

	private IMethod getIMethodFromMethodWrapper(MethodWrapper m) {
		try {
			IMember im = m.getMember();
			if (im.getElementType() == IJavaElement.METHOD)
				return (IMethod) m.getMember();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void findMethodsCount() throws JavaModelException {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		System.out.println("root " + root.getLocation().toOSString());
		
		IProject[] projects = root.getProjects();
		
		for (IProject proj : projects) {
			System.out.println("Project name: " + proj.getName());
			
			IJavaProject javaProj = JavaCore.create(proj);
			IPackageFragment[] packages = javaProj.getPackageFragments();
			
			for (IPackageFragment apack: packages) {
				if (apack.getKind() == IPackageFragmentRoot.K_SOURCE) {
					for (ICompilationUnit unit : apack.getCompilationUnits()) {
						System.out.println("-- class name " + unit.getElementName());
						
						IType[] allTypes = unit.getAllTypes();
						for (IType type : allTypes) {
							IMethod[] methods = type.getMethods();
							
							for (IMethod method : methods) {
								projectMethods.add(method);
								System.out.println("--method name: " + method.getElementName());
								System.out.println("signature: " + method.getSignature());
								System.out.println("--return type: " + method.getReturnType());
								System.out.println("new: "+ method.getPath().toString());
							}
						}
					}
				}
			}
		}
	}
		
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {		
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);		
		try {
			findMethodsCount();
			
			String result = "List of methods in the workspace and how many calls it gets: \n\n";
			
			for (IMethod meth : projectMethods) {				
				HashSet<IMethod> callers = getCallersOf(meth);
				String methodName = meth.getElementName();
				String type = Character.isUpperCase(methodName.charAt(0)) ? "Constructor " : "Method ";
				result += type + methodName + "()\n\tHow many calls: " + callers.size() + "\n\n";
			};
			
			MessageDialog.openInformation(
					window.getShell(),
					"Method/Call Counter",
					result);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return null;
	}
}
