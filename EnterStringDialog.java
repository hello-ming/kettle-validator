package com.zte.kettle.jobentry;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.pentaho.di.i18n.BaseMessages;

public class EnterStringDialog extends Dialog {
	private static Class<?> PKG = EnterStringDialog.class; // for i18n purposes,
															// needed by
															// Translator2!!

	String result;
	private Shell shell;
	private String title = "EnterString Dialog";
	private String message = "Please enter string";
	private Shell parent;
	private Text wInput;

	public EnterStringDialog(Shell parent, int style) {
		super(parent, style);
	}

	public EnterStringDialog(Shell parent) {
		this(parent, 0);
	}

	public EnterStringDialog(Shell parent, String title, String message) {
		this(parent, 0);
		this.title = title;
		this.message = message;
	}

	public String open() {
		parent = getParent();
		shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | SWT.RESIZE | SWT.MIN | SWT.MAX);
		shell.setText(this.title);
		FormLayout formLayout = new FormLayout();
		formLayout.marginWidth = 10;
		formLayout.marginHeight = 10;
		formLayout.spacing = 10;
		shell.setLayout(formLayout);

		Label wMessage = new Label(shell, SWT.LEFT);
		wMessage.setText(this.message);
		FormData wrMessage = new FormData();
		wrMessage.top = new FormAttachment(0);
		wrMessage.left = new FormAttachment(0);
		wMessage.setLayoutData(wrMessage);

		wInput = new Text(shell, SWT.NONE);
		FormData wrInput = new FormData();
		wrInput.top = new FormAttachment(wMessage, 5);
		wrInput.left = new FormAttachment(0);
		wrInput.right = new FormAttachment(100);
		wInput.setLayoutData(wrInput);
		wInput.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected( SelectionEvent e ){
				ok();
			}
		});

		Button wOK = new Button(shell, SWT.PUSH);
		wOK.setText(BaseMessages.getString(PKG, "System.Button.OK"));
		FormData wrOk = new FormData();
		wrOk.top = new FormAttachment(wInput);
		wrOk.right = new FormAttachment(50, -5);
		wOK.setLayoutData(wrOk);
		wOK.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				ok();
			}
		});

		Button wCancel = new Button(shell, SWT.PUSH);
		wCancel.setText(BaseMessages.getString(PKG, "System.Button.Cancel"));
		FormData wrCancel = new FormData();
		wrCancel.top = new FormAttachment(wInput);
		wrCancel.left = new FormAttachment(50, 5);
		wCancel.setLayoutData(wrCancel);
		wCancel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				cancel();
			}
		});

		shell.pack();
		shell.open();
		Display display = parent.getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		return result;

	}

	private void cancel() {
		result = null;
		shell.dispose();
	}

	private void ok() {
		result = wInput.getText();
		shell.dispose();
	}

	public static void center(Shell shell) {
		Rectangle bds = shell.getDisplay().getBounds();
		Point p = shell.getSize();
		int nLeft = (bds.width - p.x) / 2;
		int nTop = (bds.height - p.y) / 2;
		shell.setBounds(nLeft, nTop, p.x, p.y);
	}

	public static void main(String[] argc) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("main window");
		shell.setLayout(new FormLayout());
		Composite comp = new Composite(shell, SWT.NONE);
		comp.setLayoutData(new FormData(400, 300));
		comp.setLayout(new FormLayout());

		Button btn = new Button(comp, SWT.PUSH);
		btn.setText("click me");
		FormData bForm = new FormData(200, 100);
		bForm.left = new FormAttachment(50, -100);
		bForm.bottom = new FormAttachment(100, -50);
		btn.setLayoutData(bForm);

		btn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				EnterStringDialog dia = new EnterStringDialog(new Shell(), "tttt", "aaaaaaxxxxxxxxxxxxxxxxxxxxxxaaaaaaaaabbbbbbbbbbbbbbb");
				String result = dia.open();
				if (result == null) {
					System.out.println("canceled!");
				} else {
					System.out.println(result);
				}

			}
		});
		shell.pack();
		center(shell);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

}
