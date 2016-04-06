package net.driftingcolossus.phonebeats.desktop;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class StartShell extends Shell {
	private Text text;
	private Text text_1;


	/**
	 * Create the shell.
	 * @param display
	 */
	public StartShell(Display display) {
		super(display, SWT.TITLE);
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		composite.setBounds(0, 0, 444, 80);
		
		Label lblPhoneBeatsStart = new Label(composite, SWT.NONE);
		lblPhoneBeatsStart.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD));
		lblPhoneBeatsStart.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		lblPhoneBeatsStart.setBounds(10, 10, 259, 30);
		lblPhoneBeatsStart.setText("Phone Beats Start Options");
		
		Label lblScreenWidth = new Label(this, SWT.NONE);
		lblScreenWidth.setBounds(10, 105, 87, 15);
		lblScreenWidth.setText("Screen Width:");
		
		text = new Text(this, SWT.BORDER);
		text.setBounds(103, 102, 331, 21);
		
		Label lblScreenHeight = new Label(this, SWT.NONE);
		lblScreenHeight.setText("Screen Height:");
		lblScreenHeight.setBounds(10, 129, 87, 15);
		
		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(103, 126, 331, 21);
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Start Options");
		setSize(450, 490);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
