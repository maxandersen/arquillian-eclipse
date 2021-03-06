/*************************************************************************************
 * Copyright (c) 2008-2012 Red Hat, Inc. and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     JBoss by Red Hat - Initial implementation.
 ************************************************************************************/
package org.jboss.tools.arquillian.ui.internal.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 * 
 * @author snjeza
 *
 */
public abstract class ArquillianAbstractHandler extends AbstractHandler {

	protected IProject getProject(ExecutionEvent event) {
		ISelection sel= HandlerUtil.getCurrentSelection(event);
		IProject project = null;
		if (sel instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection = (IStructuredSelection) sel;
			Object object = structuredSelection.getFirstElement();
			IResource resource = null;
			if (object instanceof IResource) {
				resource = (IResource) object;
			} else  if (object instanceof IAdaptable) {
				Object res= ((IAdaptable) object).getAdapter(IResource.class);
				if (res != null) {
					resource = (IResource) res;
				}
			}
			if (resource != null) {
				project = resource.getProject();
			}
			
		}
		return project;
	}

	protected Shell getShell() {
		if (Display.getCurrent() != null) {
			return PlatformUI.getWorkbench().getModalDialogShellProvider().getShell();
		}
		return null;
	}
}
