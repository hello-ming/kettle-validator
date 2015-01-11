package com.zte.kettle.jobentry;

import java.io.File;
import java.util.ArrayList;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.pentaho.di.core.Const;
import org.pentaho.di.i18n.BaseMessages;
import org.pentaho.di.trans.steps.validator.Validation;
import org.pentaho.di.trans.steps.validator.ValidatorMeta;
import org.pentaho.di.ui.core.dialog.ErrorDialog;

public class CustomDialog extends Dialog {
	private static Class<?> PKG = ValidatorMeta.class; // for i18n purposes,
														// needed by
														// Translator2!!

	private List wValidationsList;
	private Validation selectedField;
	private java.util.List<Validation> selectionList;

	Shell shell;

	private Label wlDescription;
	private Text wDescription;
	private Label wlFieldName;
	private CCombo wFieldName;
	private Label wlNullAllowed;
	private Button wNullAllowed;
	private Label wlOnlyNullAllowed;
	private Button wOnlyNullAllowed;
	private Label wlOnlyNumeric;
	private Button wOnlyNumeric;
	private Label wlMaxLength;
	private Text wMaxLength;
	private Label wlMinLength;
	private Text wMinLength;
	private Group wgData;
	private Group wgType;
	private Label wlDataTypeVerified;
	private Button wDataTypeVerified;
	private Label wlDataType;
	private Combo wDataType;
	private Label wlConversionMask;
	private Text wConversionMask;
	private Label wlDecimalSymbol;
	private Text wDecimalSymbol;
	private Label wlGroupingSymbol;
	private Text wGroupingSymbol;
	private Label wlMaxValue;
	private Text wMaxValue;
	private Label wlMinValue;
	private Text wMinValue;
	private Label wlAllowedValues;
	private List wAllowedValues;
	private Button wbAddAllowed;
	private Button wbRemoveAllowed;
	private Label wlStartStringExpected;
	private Text wStartStringExpected;
	private Label wlEndStringExpected;
	private Text wEndStringExpected;
	private Label wlStartStringDisallowed;
	private Text wStartStringDisallowed;
	private Label wlEndStringDisallowed;
	private Text wEndStringDisallowed;
	private Label wlRegExpExpected;
	private Text wRegExpExpected;
	private Label wlRegExpDisallowed;
	private Text wRegExpDisallowed;
	private Label wlErrorCode;
	private Text wErrorCode;
	private Label wlErrorDescription;
	private Text wErrorDescription;
	private Composite btnBar;
	private Button wOK;
	private Button wCancel;
	private Button wClear;
	private Button wNew;

	private Label wlRuleFilePath;

	private Text wRuleFilePath;
	private String ruleFilePath;

	private Label wlMaxErrors;
	private Text wMaxErrors;
	private String MaxErrors = "300";

	private Label wlFileType;
	private Combo wFileType;
	private String FileType = "CSV";

	private Label wlFieldSeparator;
	private Text wFieldSeparator;
	private String FieldSeparator = ",";

	private CustomDialog(Shell parent, int style) {
		super(parent, style);
		selectedField = null;
		selectionList = new ArrayList<Validation>();
	}

	public CustomDialog(Shell parent, String ruleFilePath) {
		this(parent, 0);
		this.ruleFilePath = ruleFilePath;
	}

	public void open() {
		Shell parent = getParent();
		shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL | SWT.RESIZE | SWT.MIN | SWT.MAX);
		shell.setText("custom dialog");
		FormLayout layout = new FormLayout();
		layout.marginWidth = 5;
		layout.marginHeight = 5;
		shell.setLayout(layout);

		// //////////////////////////////////////////////////////////////////////////////////////
		int middle = 30;
		int margin = 4;
		int extra = 10;

		// Rule File Path
		wlRuleFilePath = new Label(shell, SWT.RIGHT);
		wlRuleFilePath.setText("Rule File Path");
		FormData fdlRuleFilePath = new FormData();
		fdlRuleFilePath.left = new FormAttachment(0);
		fdlRuleFilePath.right = new FormAttachment(middle);
		fdlRuleFilePath.top = new FormAttachment(0, 0);
		wlRuleFilePath.setLayoutData(fdlRuleFilePath);
		wRuleFilePath = new Text(shell, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		FormData fdRuleFilePath = new FormData();
		fdRuleFilePath.left = new FormAttachment(middle, margin + extra);
		fdRuleFilePath.right = new FormAttachment(100, 0);
		fdRuleFilePath.top = new FormAttachment(0, 0);
		wRuleFilePath.setLayoutData(fdRuleFilePath);
		wRuleFilePath.setText(this.ruleFilePath);
		wRuleFilePath.setEnabled(false);

		btnBar = new Composite(shell, SWT.NONE);
		FormData fdBtn = new FormData();
		fdBtn.left = new FormAttachment(0, 0);
		fdBtn.right = new FormAttachment(100, 0);
		fdBtn.top = new FormAttachment(100, -50);
		fdBtn.bottom = new FormAttachment(100);
		btnBar.setLayoutData(fdBtn);

		int leftMiddle = 10;

		 // Field separator
		 wlFieldSeparator = new Label(shell, SWT.RIGHT);
		 wlFieldSeparator.setText("Field Separator");
		 FormData fdlFieldSeparator = new FormData();
		 fdlFieldSeparator.left = new FormAttachment(0);
		 fdlFieldSeparator.right = new FormAttachment(leftMiddle);
		 fdlFieldSeparator.bottom = new FormAttachment(btnBar, -margin*2);
		 wlFieldSeparator.setLayoutData(fdlFieldSeparator);
		 wFieldSeparator = new Text(shell, SWT.SINGLE | SWT.LEFT |
		 SWT.BORDER);
		 FormData fdFieldSeparator = new FormData();
		 fdFieldSeparator.left = new FormAttachment(leftMiddle, margin);
		 fdFieldSeparator.right = new FormAttachment(middle, -margin);
		 fdFieldSeparator.bottom = new FormAttachment(btnBar, -margin*2);
		 wFieldSeparator.setLayoutData(fdFieldSeparator);
		 wFieldSeparator.setText(this.FieldSeparator);
		
		 // File type
		 wlFileType = new Label(shell, SWT.RIGHT);
		 wlFileType.setText("File Type");
		 FormData fdlFileType = new FormData();
		 fdlFileType.left = new FormAttachment(0);
		 fdlFileType.right = new FormAttachment(leftMiddle);
		 fdlFileType.bottom = new FormAttachment(wFieldSeparator, -margin);
		 wlFileType.setLayoutData(fdlFileType);
		 wFileType = new Combo(shell, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		 wFileType.setItems(new String[]{"CSV", "TXT"});
		 FormData fdFileType = new FormData();
		 fdFileType.left = new FormAttachment(leftMiddle, margin);
		 fdFileType.right = new FormAttachment(middle, -margin);
		 fdFileType.bottom = new FormAttachment(wFieldSeparator, -margin);
		 wFileType.setLayoutData(fdFileType);
		 wFileType.setText(this.FileType);
		
		 // Max Errors
		 wlMaxErrors = new Label(shell, SWT.RIGHT);
		 wlMaxErrors.setText("Max Errors");
		 FormData fdlMaxErrors = new FormData();
		 fdlMaxErrors.left = new FormAttachment(0);
		 fdlMaxErrors.right = new FormAttachment(leftMiddle);
		 fdlMaxErrors.bottom = new FormAttachment(wFileType, -margin);
		 wlMaxErrors.setLayoutData(fdlMaxErrors);
		 wMaxErrors = new Text(shell, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		 FormData fdMaxErrors = new FormData();
		 fdMaxErrors.left = new FormAttachment(leftMiddle, margin);
		 fdMaxErrors.right = new FormAttachment(middle, -margin);
		 fdMaxErrors.bottom = new FormAttachment(wFileType, -margin);
		 wMaxErrors.setLayoutData(fdMaxErrors);
		 wMaxErrors.setText(this.MaxErrors);

		// List of fields to the left...
		Label wlFieldList = new Label(shell, SWT.LEFT);
		wlFieldList.setText(BaseMessages.getString(PKG, "ValidatorDialog.FieldList.Label"));
		FormData fdlFieldList = new FormData();
		fdlFieldList.left = new FormAttachment(0, 0);
		fdlFieldList.right = new FormAttachment(middle, -margin);
		fdlFieldList.top = new FormAttachment(wlRuleFilePath, margin);
		wlFieldList.setLayoutData(fdlFieldList);
		wValidationsList = new List(shell, SWT.SINGLE | SWT.LEFT | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		wValidationsList.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				showSelectedValidatorField(wValidationsList.getSelection()[0]);
			}
		});
		FormData fdFieldList = new FormData();
		fdFieldList.left = new FormAttachment(0, 0);
		fdFieldList.top = new FormAttachment(wlFieldList, margin);
		fdFieldList.right = new FormAttachment(middle, -margin);
		fdFieldList.bottom = new FormAttachment(wMaxErrors, -margin * 2);
		wValidationsList.setLayoutData(fdFieldList);


		// Create a scrolled composite on the right side...
		ScrolledComposite wSComp = new ScrolledComposite(shell, SWT.H_SCROLL | SWT.V_SCROLL);
		wSComp.setLayout(new FillLayout());
		FormData fdComp = new FormData();
		fdComp.left = new FormAttachment(middle, 0);
		fdComp.top = new FormAttachment(wRuleFilePath, 2 * margin);
		fdComp.right = new FormAttachment(100, 0);
		fdComp.bottom = new FormAttachment(btnBar, -margin * 2);
		wSComp.setLayoutData(fdComp);

		Composite wComp = new Composite(wSComp, SWT.BORDER);
		FormLayout compLayout = new FormLayout();
		compLayout.marginWidth = 3;
		compLayout.marginHeight = 3;
		wComp.setLayout(compLayout);

		// Description (list key)
		wlDescription = new Label(wComp, SWT.RIGHT);
		wlDescription.setText(BaseMessages.getString(PKG, "ValidatorDialog.Description.Label"));
		FormData fdlDescription = new FormData();
		fdlDescription.left = new FormAttachment(0, 0);
		fdlDescription.right = new FormAttachment(middle, -margin);
		fdlDescription.top = new FormAttachment(0, 0);
		wlDescription.setLayoutData(fdlDescription);
		wDescription = new Text(wComp, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		FormData fdDescription = new FormData();
		fdDescription.left = new FormAttachment(middle, margin + extra);
		fdDescription.right = new FormAttachment(100, 0);
		fdDescription.top = new FormAttachment(0, 0);
		wDescription.setLayoutData(fdDescription);
		wDescription.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent event) {
				// See if there is a selected Validation
				//
				if (wValidationsList != null && wValidationsList.getItemCount() > 0 && wValidationsList.getSelection().length == 1) {
					int index = wValidationsList.getSelectionIndex();
					String description = wValidationsList.getItem(index);
					Validation validation = Validation.findValidation(selectionList, description);
					String newDescription = wDescription.getText();
					validation.setName(newDescription);
					wValidationsList.setItem(index, newDescription);
					wValidationsList.select(index);
				}
			}
		});

		// The name of the field to validate
		wlFieldName = new Label(wComp, SWT.RIGHT);
		wlFieldName.setText(BaseMessages.getString(PKG, "ValidatorDialog.FieldName.Label"));
		FormData fdlFieldName = new FormData();
		fdlFieldName.left = new FormAttachment(0, 0);
		fdlFieldName.right = new FormAttachment(middle, -margin);
		fdlFieldName.top = new FormAttachment(wDescription, margin);
		wlFieldName.setLayoutData(fdlFieldName);
		wFieldName = new CCombo(wComp, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		FormData fdFieldName = new FormData();
		fdFieldName.left = new FormAttachment(middle, margin + extra);
		fdFieldName.right = new FormAttachment(100, 0);
		fdFieldName.top = new FormAttachment(wDescription, margin);
		wFieldName.setLayoutData(fdFieldName);

		// TODO: grab field list in thread in the background...
		//
		try {
			String[] inputFields = new String[] { "No.", "Name", "Gender", "BirthDay" };
			wFieldName.setItems(inputFields);
		} catch (Exception ex) {
			new ErrorDialog(shell, BaseMessages.getString(PKG, "ValidatorDialog.Exception.CantGetFieldsFromPreviousSteps.Title"),
					BaseMessages.getString(PKG, "ValidatorDialog.Exception.CantGetFieldsFromPreviousSteps.Message"), ex);
		}

		// ErrorCode
		wlErrorCode = new Label(wComp, SWT.RIGHT);
		wlErrorCode.setText(BaseMessages.getString(PKG, "ValidatorDialog.ErrorCode.Label"));
		FormData fdlErrorCode = new FormData();
		fdlErrorCode.left = new FormAttachment(0, 0);
		fdlErrorCode.right = new FormAttachment(middle, -margin);
		fdlErrorCode.top = new FormAttachment(wFieldName, margin);
		wlErrorCode.setLayoutData(fdlErrorCode);
		wErrorCode = new Text(wComp, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		FormData fdErrorCode = new FormData();
		fdErrorCode.left = new FormAttachment(middle, margin);
		fdErrorCode.right = new FormAttachment(100, 0);
		fdErrorCode.top = new FormAttachment(wFieldName, margin);
		wErrorCode.setLayoutData(fdErrorCode);

		// ErrorDescription
		wlErrorDescription = new Label(wComp, SWT.RIGHT);
		wlErrorDescription.setText(BaseMessages.getString(PKG, "ValidatorDialog.ErrorDescription.Label"));
		FormData fdlErrorDescription = new FormData();
		fdlErrorDescription.left = new FormAttachment(0, 0);
		fdlErrorDescription.right = new FormAttachment(middle, -margin);
		fdlErrorDescription.top = new FormAttachment(wErrorCode, margin);
		wlErrorDescription.setLayoutData(fdlErrorDescription);
		wErrorDescription = new Text(wComp, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		FormData fdErrorDescription = new FormData();
		fdErrorDescription.left = new FormAttachment(middle, margin);
		fdErrorDescription.right = new FormAttachment(100, 0);
		fdErrorDescription.top = new FormAttachment(wErrorCode, margin);
		wErrorDescription.setLayoutData(fdErrorDescription);

		// Data type validations & constants masks...
		wgType = new Group(wComp, SWT.NONE);
		wgType.setText(BaseMessages.getString(PKG, "ValidatorDialog.TypeGroup.Label"));
		FormLayout typeGroupLayout = new FormLayout();
		typeGroupLayout.marginHeight = Const.FORM_MARGIN;
		typeGroupLayout.marginWidth = Const.FORM_MARGIN;
		wgType.setLayout(typeGroupLayout);
		FormData fdType = new FormData();
		fdType.left = new FormAttachment(0, 0);
		fdType.right = new FormAttachment(100, 0);
		fdType.top = new FormAttachment(wErrorDescription, margin * 2);
		wgType.setLayoutData(fdType);

		// Check for data type correctness?
		wlDataTypeVerified = new Label(wgType, SWT.RIGHT);
		wlDataTypeVerified.setText(BaseMessages.getString(PKG, "ValidatorDialog.DataTypeVerified.Label"));
		FormData fdldataTypeVerified = new FormData();
		fdldataTypeVerified.left = new FormAttachment(0, 0);
		fdldataTypeVerified.right = new FormAttachment(middle, -margin);
		fdldataTypeVerified.top = new FormAttachment(0, 0);
		wlDataTypeVerified.setLayoutData(fdldataTypeVerified);
		wDataTypeVerified = new Button(wgType, SWT.CHECK);
		FormData fddataTypeVerified = new FormData();
		fddataTypeVerified.left = new FormAttachment(middle, margin + extra);
		fddataTypeVerified.right = new FormAttachment(100, 0);
		fddataTypeVerified.top = new FormAttachment(0, 0);
		wDataTypeVerified.setLayoutData(fddataTypeVerified);

		// Data type
		wlDataType = new Label(wgType, SWT.RIGHT);
		wlDataType.setText(BaseMessages.getString(PKG, "ValidatorDialog.DataType.Label"));
		FormData fdlDataType = new FormData();
		fdlDataType.left = new FormAttachment(0, 0);
		fdlDataType.right = new FormAttachment(middle, -margin);
		fdlDataType.top = new FormAttachment(wDataTypeVerified, margin);
		wlDataType.setLayoutData(fdlDataType);
		wDataType = new Combo(wgType, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		wDataType.setItems(ValueMeta.getTypes());
		FormData fdDataType = new FormData();
		fdDataType.left = new FormAttachment(middle, margin + extra);
		fdDataType.right = new FormAttachment(100, 0);
		fdDataType.top = new FormAttachment(wDataTypeVerified, margin);
		wDataType.setLayoutData(fdDataType);

		// Conversion mask
		wlConversionMask = new Label(wgType, SWT.RIGHT);
		wlConversionMask.setText(BaseMessages.getString(PKG, "ValidatorDialog.ConversionMask.Label"));
		FormData fdlConversionMask = new FormData();
		fdlConversionMask.left = new FormAttachment(0, 0);
		fdlConversionMask.right = new FormAttachment(middle, -margin);
		fdlConversionMask.top = new FormAttachment(wDataType, margin);
		wlConversionMask.setLayoutData(fdlConversionMask);
		wConversionMask = new Text(wgType, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		FormData fdConversionMask = new FormData();
		fdConversionMask.left = new FormAttachment(middle, margin);
		fdConversionMask.right = new FormAttachment(100, 0);
		fdConversionMask.top = new FormAttachment(wDataType, margin);
		wConversionMask.setLayoutData(fdConversionMask);

		// Decimal Symbol
		wlDecimalSymbol = new Label(wgType, SWT.RIGHT);
		wlDecimalSymbol.setText(BaseMessages.getString(PKG, "ValidatorDialog.DecimalSymbol.Label"));
		FormData fdlDecimalSymbol = new FormData();
		fdlDecimalSymbol.left = new FormAttachment(0, 0);
		fdlDecimalSymbol.right = new FormAttachment(middle, -margin);
		fdlDecimalSymbol.top = new FormAttachment(wConversionMask, margin);
		wlDecimalSymbol.setLayoutData(fdlDecimalSymbol);
		wDecimalSymbol = new Text(wgType, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		FormData fdDecimalSymbol = new FormData();
		fdDecimalSymbol.left = new FormAttachment(middle, margin);
		fdDecimalSymbol.right = new FormAttachment(100, 0);
		fdDecimalSymbol.top = new FormAttachment(wConversionMask, margin);
		wDecimalSymbol.setLayoutData(fdDecimalSymbol);

		// Grouping Symbol
		wlGroupingSymbol = new Label(wgType, SWT.RIGHT);
		wlGroupingSymbol.setText(BaseMessages.getString(PKG, "ValidatorDialog.GroupingSymbol.Label"));
		FormData fdlGroupingSymbol = new FormData();
		fdlGroupingSymbol.left = new FormAttachment(0, 0);
		fdlGroupingSymbol.right = new FormAttachment(middle, -margin);
		fdlGroupingSymbol.top = new FormAttachment(wDecimalSymbol, margin);
		wlGroupingSymbol.setLayoutData(fdlGroupingSymbol);
		wGroupingSymbol = new Text(wgType, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		FormData fdGroupingSymbol = new FormData();
		fdGroupingSymbol.left = new FormAttachment(middle, margin);
		fdGroupingSymbol.right = new FormAttachment(100, 0);
		fdGroupingSymbol.top = new FormAttachment(wDecimalSymbol, margin);
		wGroupingSymbol.setLayoutData(fdGroupingSymbol);

		// /////////////////////////////////////////////////////////////////////////////////////////////////
		//
		// The data group...
		wgData = new Group(wComp, SWT.NONE);
		wgData.setText(BaseMessages.getString(PKG, "ValidatorDialog.DataGroup.Label"));
		FormLayout dataGroupLayout = new FormLayout();
		dataGroupLayout.marginHeight = Const.FORM_MARGIN;
		dataGroupLayout.marginWidth = Const.FORM_MARGIN;
		wgData.setLayout(dataGroupLayout);
		FormData fdData = new FormData();
		fdData.left = new FormAttachment(0, 0);
		fdData.right = new FormAttachment(100, 0);
		fdData.top = new FormAttachment(wgType, margin);
		wgData.setLayoutData(fdData);

		// Check for null?
		wlNullAllowed = new Label(wgData, SWT.RIGHT);
		wlNullAllowed.setText(BaseMessages.getString(PKG, "ValidatorDialog.NullAllowed.Label"));
		FormData fdlNullAllowed = new FormData();
		fdlNullAllowed.left = new FormAttachment(0, 0);
		fdlNullAllowed.right = new FormAttachment(middle, -margin);
		fdlNullAllowed.top = new FormAttachment(0, 0);
		wlNullAllowed.setLayoutData(fdlNullAllowed);
		wNullAllowed = new Button(wgData, SWT.CHECK);
		FormData fdNullAllowed = new FormData();
		fdNullAllowed.left = new FormAttachment(middle, margin + extra);
		fdNullAllowed.right = new FormAttachment(100, 0);
		fdNullAllowed.top = new FormAttachment(0, 0);
		wNullAllowed.setLayoutData(fdNullAllowed);

		// Only null allowed?
		wlOnlyNullAllowed = new Label(wgData, SWT.RIGHT);
		wlOnlyNullAllowed.setText(BaseMessages.getString(PKG, "ValidatorDialog.OnlyNullAllowed.Label"));
		FormData fdlOnlyNullAllowed = new FormData();
		fdlOnlyNullAllowed.left = new FormAttachment(0, 0);
		fdlOnlyNullAllowed.right = new FormAttachment(middle, -margin);
		fdlOnlyNullAllowed.top = new FormAttachment(wNullAllowed, margin);
		wlOnlyNullAllowed.setLayoutData(fdlOnlyNullAllowed);
		wOnlyNullAllowed = new Button(wgData, SWT.CHECK);
		FormData fdOnlyNullAllowed = new FormData();
		fdOnlyNullAllowed.left = new FormAttachment(middle, margin + extra);
		fdOnlyNullAllowed.right = new FormAttachment(100, 0);
		fdOnlyNullAllowed.top = new FormAttachment(wNullAllowed, margin);
		wOnlyNullAllowed.setLayoutData(fdOnlyNullAllowed);

		// Only numeric allowed?
		wlOnlyNumeric = new Label(wgData, SWT.RIGHT);
		wlOnlyNumeric.setText(BaseMessages.getString(PKG, "ValidatorDialog.OnlyNumeric.Label"));
		FormData fdlOnlyNumeric = new FormData();
		fdlOnlyNumeric.left = new FormAttachment(0, 0);
		fdlOnlyNumeric.right = new FormAttachment(middle, -margin);
		fdlOnlyNumeric.top = new FormAttachment(wOnlyNullAllowed, margin);
		wlOnlyNumeric.setLayoutData(fdlOnlyNumeric);
		wOnlyNumeric = new Button(wgData, SWT.CHECK);
		FormData fdOnlyNumeric = new FormData();
		fdOnlyNumeric.left = new FormAttachment(middle, margin + extra);
		fdOnlyNumeric.right = new FormAttachment(100, 0);
		fdOnlyNumeric.top = new FormAttachment(wOnlyNullAllowed, margin);
		wOnlyNumeric.setLayoutData(fdOnlyNumeric);

		// Maximum length
		wlMaxLength = new Label(wgData, SWT.RIGHT);
		wlMaxLength.setText(BaseMessages.getString(PKG, "ValidatorDialog.MaxLength.Label"));
		FormData fdlMaxLength = new FormData();
		fdlMaxLength.left = new FormAttachment(0, 0);
		fdlMaxLength.right = new FormAttachment(middle, -margin);
		fdlMaxLength.top = new FormAttachment(wOnlyNumeric, margin);
		wlMaxLength.setLayoutData(fdlMaxLength);
		wMaxLength = new Text(wgData, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		FormData fdMaxLength = new FormData();
		fdMaxLength.left = new FormAttachment(middle, margin);
		fdMaxLength.right = new FormAttachment(100, 0);
		fdMaxLength.top = new FormAttachment(wOnlyNumeric, margin);
		wMaxLength.setLayoutData(fdMaxLength);

		// Minimum length
		wlMinLength = new Label(wgData, SWT.RIGHT);
		wlMinLength.setText(BaseMessages.getString(PKG, "ValidatorDialog.MinLength.Label"));
		FormData fdlMinLength = new FormData();
		fdlMinLength.left = new FormAttachment(0, 0);
		fdlMinLength.right = new FormAttachment(middle, -margin);
		fdlMinLength.top = new FormAttachment(wMaxLength, margin);
		wlMinLength.setLayoutData(fdlMinLength);
		wMinLength = new Text(wgData, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		FormData fdMinLength = new FormData();
		fdMinLength.left = new FormAttachment(middle, margin);
		fdMinLength.right = new FormAttachment(100, 0);
		fdMinLength.top = new FormAttachment(wMaxLength, margin);
		wMinLength.setLayoutData(fdMinLength);

		// Maximum value
		wlMaxValue = new Label(wgData, SWT.RIGHT);
		wlMaxValue.setText(BaseMessages.getString(PKG, "ValidatorDialog.MaxValue.Label"));
		FormData fdlMaxValue = new FormData();
		fdlMaxValue.left = new FormAttachment(0, 0);
		fdlMaxValue.right = new FormAttachment(middle, -margin);
		fdlMaxValue.top = new FormAttachment(wMinLength, margin);
		wlMaxValue.setLayoutData(fdlMaxValue);
		wMaxValue = new Text(wgData, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		FormData fdMaxValue = new FormData();
		fdMaxValue.left = new FormAttachment(middle, margin);
		fdMaxValue.right = new FormAttachment(100, 0);
		fdMaxValue.top = new FormAttachment(wMinLength, margin);
		wMaxValue.setLayoutData(fdMaxValue);

		// Minimum value
		wlMinValue = new Label(wgData, SWT.RIGHT);
		wlMinValue.setText(BaseMessages.getString(PKG, "ValidatorDialog.MinValue.Label"));
		FormData fdlMinValue = new FormData();
		fdlMinValue.left = new FormAttachment(0, 0);
		fdlMinValue.right = new FormAttachment(middle, -margin);
		fdlMinValue.top = new FormAttachment(wMaxValue, margin);
		wlMinValue.setLayoutData(fdlMinValue);
		wMinValue = new Text(wgData, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		FormData fdMinValue = new FormData();
		fdMinValue.left = new FormAttachment(middle, margin);
		fdMinValue.right = new FormAttachment(100, 0);
		fdMinValue.top = new FormAttachment(wMaxValue, margin);
		wMinValue.setLayoutData(fdMinValue);

		// Expected start string
		wlStartStringExpected = new Label(wgData, SWT.RIGHT);
		wlStartStringExpected.setText(BaseMessages.getString(PKG, "ValidatorDialog.StartStringExpected.Label"));
		FormData fdlStartStringExpected = new FormData();
		fdlStartStringExpected.left = new FormAttachment(0, 0);
		fdlStartStringExpected.right = new FormAttachment(middle, -margin);
		fdlStartStringExpected.top = new FormAttachment(wMinValue, margin);
		wlStartStringExpected.setLayoutData(fdlStartStringExpected);
		wStartStringExpected = new Text(wgData, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		FormData fdStartStringExpected = new FormData();
		fdStartStringExpected.left = new FormAttachment(middle, margin);
		fdStartStringExpected.right = new FormAttachment(100, 0);
		fdStartStringExpected.top = new FormAttachment(wMinValue, margin);
		wStartStringExpected.setLayoutData(fdStartStringExpected);

		// Expected End string
		wlEndStringExpected = new Label(wgData, SWT.RIGHT);
		wlEndStringExpected.setText(BaseMessages.getString(PKG, "ValidatorDialog.EndStringExpected.Label"));
		FormData fdlEndStringExpected = new FormData();
		fdlEndStringExpected.left = new FormAttachment(0, 0);
		fdlEndStringExpected.right = new FormAttachment(middle, -margin);
		fdlEndStringExpected.top = new FormAttachment(wStartStringExpected, margin);
		wlEndStringExpected.setLayoutData(fdlEndStringExpected);
		wEndStringExpected = new Text(wgData, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		FormData fdEndStringExpected = new FormData();
		fdEndStringExpected.left = new FormAttachment(middle, margin);
		fdEndStringExpected.right = new FormAttachment(100, 0);
		fdEndStringExpected.top = new FormAttachment(wStartStringExpected, margin);
		wEndStringExpected.setLayoutData(fdEndStringExpected);

		// Disallowed start string
		wlStartStringDisallowed = new Label(wgData, SWT.RIGHT);
		wlStartStringDisallowed.setText(BaseMessages.getString(PKG, "ValidatorDialog.StartStringDisallowed.Label"));
		FormData fdlStartStringDisallowed = new FormData();
		fdlStartStringDisallowed.left = new FormAttachment(0, 0);
		fdlStartStringDisallowed.right = new FormAttachment(middle, -margin);
		fdlStartStringDisallowed.top = new FormAttachment(wEndStringExpected, margin);
		wlStartStringDisallowed.setLayoutData(fdlStartStringDisallowed);
		wStartStringDisallowed = new Text(wgData, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		FormData fdStartStringDisallowed = new FormData();
		fdStartStringDisallowed.left = new FormAttachment(middle, margin);
		fdStartStringDisallowed.right = new FormAttachment(100, 0);
		fdStartStringDisallowed.top = new FormAttachment(wEndStringExpected, margin);
		wStartStringDisallowed.setLayoutData(fdStartStringDisallowed);

		// Disallowed End string
		wlEndStringDisallowed = new Label(wgData, SWT.RIGHT);
		wlEndStringDisallowed.setText(BaseMessages.getString(PKG, "ValidatorDialog.EndStringDisallowed.Label"));
		FormData fdlEndStringDisallowed = new FormData();
		fdlEndStringDisallowed.left = new FormAttachment(0, 0);
		fdlEndStringDisallowed.right = new FormAttachment(middle, -margin);
		fdlEndStringDisallowed.top = new FormAttachment(wStartStringDisallowed, margin);
		wlEndStringDisallowed.setLayoutData(fdlEndStringDisallowed);
		wEndStringDisallowed = new Text(wgData, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		FormData fdEndStringDisallowed = new FormData();
		fdEndStringDisallowed.left = new FormAttachment(middle, margin);
		fdEndStringDisallowed.right = new FormAttachment(100, 0);
		fdEndStringDisallowed.top = new FormAttachment(wStartStringDisallowed, margin);
		wEndStringDisallowed.setLayoutData(fdEndStringDisallowed);

		// Expected regular expression
		wlRegExpExpected = new Label(wgData, SWT.RIGHT);
		wlRegExpExpected.setText(BaseMessages.getString(PKG, "ValidatorDialog.RegExpExpected.Label"));
		FormData fdlRegExpExpected = new FormData();
		fdlRegExpExpected.left = new FormAttachment(0, 0);
		fdlRegExpExpected.right = new FormAttachment(middle, -margin);
		fdlRegExpExpected.top = new FormAttachment(wEndStringDisallowed, margin);
		wlRegExpExpected.setLayoutData(fdlRegExpExpected);
		wRegExpExpected = new Text(wgData, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		FormData fdRegExpExpected = new FormData();
		fdRegExpExpected.left = new FormAttachment(middle, margin);
		fdRegExpExpected.right = new FormAttachment(100, 0);
		fdRegExpExpected.top = new FormAttachment(wEndStringDisallowed, margin);
		wRegExpExpected.setLayoutData(fdRegExpExpected);

		// Disallowed regular expression
		wlRegExpDisallowed = new Label(wgData, SWT.RIGHT);
		wlRegExpDisallowed.setText(BaseMessages.getString(PKG, "ValidatorDialog.RegExpDisallowed.Label"));
		FormData fdlRegExpDisallowed = new FormData();
		fdlRegExpDisallowed.left = new FormAttachment(0, 0);
		fdlRegExpDisallowed.right = new FormAttachment(middle, -margin);
		fdlRegExpDisallowed.top = new FormAttachment(wRegExpExpected, margin);
		wlRegExpDisallowed.setLayoutData(fdlRegExpDisallowed);
		wRegExpDisallowed = new Text(wgData, SWT.SINGLE | SWT.LEFT | SWT.BORDER);
		FormData fdRegExpDisallowed = new FormData();
		fdRegExpDisallowed.left = new FormAttachment(middle, margin);
		fdRegExpDisallowed.right = new FormAttachment(100, 0);
		fdRegExpDisallowed.top = new FormAttachment(wRegExpExpected, margin);
		wRegExpDisallowed.setLayoutData(fdRegExpDisallowed);

		// Allowed values: a list box.
		//
		// Add an entry
		wbAddAllowed = new Button(wgData, SWT.PUSH);
		wbAddAllowed.setText(BaseMessages.getString(PKG, "ValidatorDialog.ButtonAddAllowed.Label"));
		FormData fdbAddAllowed = new FormData();
		fdbAddAllowed.right = new FormAttachment(100, 0);
		fdbAddAllowed.top = new FormAttachment(wRegExpDisallowed, margin);
		wbAddAllowed.setLayoutData(fdbAddAllowed);
		wbAddAllowed.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				addAllowedValue();
			}
		});

		// Remove an entry
		wbRemoveAllowed = new Button(wgData, SWT.PUSH);
		wbRemoveAllowed.setText(BaseMessages.getString(PKG, "ValidatorDialog.ButtonRemoveAllowed.Label"));
		FormData fdbRemoveAllowed = new FormData();
		fdbRemoveAllowed.right = new FormAttachment(100, 0);
		fdbRemoveAllowed.top = new FormAttachment(wbAddAllowed, margin);
		wbRemoveAllowed.setLayoutData(fdbRemoveAllowed);
		wbRemoveAllowed.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				removeAllowedValue();
			}
		});

		wlAllowedValues = new Label(wgData, SWT.RIGHT);
		wlAllowedValues.setText(BaseMessages.getString(PKG, "ValidatorDialog.AllowedValues.Label"));
		FormData fdlAllowedValues = new FormData();
		fdlAllowedValues.left = new FormAttachment(0, 0);
		fdlAllowedValues.right = new FormAttachment(middle, -margin);
		fdlAllowedValues.top = new FormAttachment(wRegExpDisallowed, margin);
		wlAllowedValues.setLayoutData(fdlAllowedValues);
		wAllowedValues = new List(wgData, SWT.MULTI | SWT.LEFT | SWT.BORDER);
		FormData fdAllowedValues = new FormData();
		fdAllowedValues.left = new FormAttachment(middle, margin + extra);
		fdAllowedValues.right = new FormAttachment(wbRemoveAllowed, -20);
		fdAllowedValues.top = new FormAttachment(wRegExpDisallowed, margin);
		fdAllowedValues.bottom = new FormAttachment(wRegExpDisallowed, 150);
		wAllowedValues.setLayoutData(fdAllowedValues);

		wComp.layout();
		wComp.pack();
		Rectangle bounds = wComp.getBounds();

		wSComp.setContent(wComp);
		wSComp.setExpandHorizontal(true);
		wSComp.setExpandVertical(true);
		wSComp.setMinWidth(bounds.width);
		wSComp.setMinHeight(bounds.height);

		FormLayout btnBarLayout = new FormLayout();
		btnBarLayout.spacing = 10;
		btnBarLayout.marginWidth = 20;
		btnBarLayout.marginHeight = 10;
		btnBar.setLayout(btnBarLayout);

		// Some buttons
		wOK = new Button(btnBar, SWT.PUSH);
		wOK.setText(BaseMessages.getString(PKG, "System.Button.OK"));
		wNew = new Button(btnBar, SWT.PUSH);
		wNew.setText(BaseMessages.getString(PKG, "ValidatorDialog.NewButton.Label"));
		wClear = new Button(btnBar, SWT.PUSH);
		wClear.setText(BaseMessages.getString(PKG, "ValidatorDialog.ClearButton.Label"));
		wCancel = new Button(btnBar, SWT.PUSH);
		wCancel.setText(BaseMessages.getString(PKG, "System.Button.Cancel"));

		FormData fdNew = new FormData();
		fdNew.right = new FormAttachment(50, -5);
		wNew.setLayoutData(fdNew);
		FormData fdOK = new FormData();
		fdOK.right = new FormAttachment(wNew);
		wOK.setLayoutData(fdOK);

		FormData fdClear = new FormData();
		fdClear.left = new FormAttachment(50, 5);
		wClear.setLayoutData(fdClear);
		FormData fdCancel = new FormData();
		fdCancel.left = new FormAttachment(wClear);
		wCancel.setLayoutData(fdCancel);

		// Add listeners
		wCancel.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				cancel();
			}
		});

		wOK.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				ok();
			}
		});

		wClear.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// Clear the validation rules for a certain field...
				int index = wValidationsList.getSelectionIndex();
				if (index >= 0) {
					selectionList.remove(index);
					selectedField = null;
					wValidationsList.remove(index);
					enableFields();
				}
			}
		});

		wNew.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// Create a new validation rule page ...
				EnterStringDialog enterStringDialog = new EnterStringDialog(shell, BaseMessages.getString(PKG,
						"ValidatorDialog.EnterValidationRuleName.Title"), BaseMessages.getString(PKG,
						"ValidatorDialog.EnterValidationRuleName.Message"));
				String description = enterStringDialog.open();
				if (description != null) {
					if (Validation.findValidation(selectionList, description) != null) {
						MessageBox messageBox = new MessageBox(shell, SWT.ICON_ERROR);
						// CHECKSTYLE:LineLength:OFF
						messageBox.setText(BaseMessages.getString(PKG, "ValidatorDialog.ValidationRuleNameAlreadyExists.Title"));
						messageBox.setMessage(BaseMessages.getString(PKG, "ValidatorDialog.ValidationRuleNameAlreadyExists.Message"));
						messageBox.open();
						return;
					}
					saveChanges();
					Validation validation = new Validation();
					validation.setName(description);
					selectionList.add(validation);
					selectedField = validation;
					refreshValidationsList();
					wValidationsList.select(selectionList.size() - 1);
					getValidatorFieldData(validation);
				}
			}
		});

		// /////////////////////////////////////////////////////////////////////////////////////////
		if (!getData()) {
			shell.dispose();
			return;
		}
		shell.pack();
		shell.open();
		Display display = parent.getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

	protected void addAllowedValue() {
		EnterStringDialog dialog = new EnterStringDialog(shell, BaseMessages.getString(PKG, "ValidatorDialog.Dialog.AddAllowedValue.Title"),
				BaseMessages.getString(PKG, "ValidatorDialog.Dialog.AddAllowedValue.Message"));
		String value = dialog.open();
		if (!Const.isEmpty(value)) {
			wAllowedValues.add(value);
		}
	}

	protected void removeAllowedValue() {
		String[] selection = wAllowedValues.getSelection();
		for (String string : selection) {
			wAllowedValues.remove(string);
		}
	}

	private void showSelectedValidatorField(String selection) {
		// Someone hit a field...
		saveChanges();
		Validation field = Validation.findValidation(selectionList, selection);
		if (field == null) {
			field = new Validation(selection);
		}
		selectedField = field;
		getValidatorFieldData(selectedField);
		enableFields();
	}

	private void getValidatorFieldData(Validation field) {

		wDescription.setText(Const.NVL(field.getName(), ""));
		wFieldName.setText(Const.NVL(field.getFieldName(), ""));

		wErrorCode.setText(Const.NVL(field.getErrorCode(), ""));
		wErrorDescription.setText(Const.NVL(field.getErrorDescription(), ""));

		wDataTypeVerified.setSelection(field.isDataTypeVerified());
		wDataType.setText(ValueMeta.getTypeDesc(field.getDataType()));
		wConversionMask.setText(Const.NVL(field.getConversionMask(), ""));
		wDecimalSymbol.setText(Const.NVL(field.getDecimalSymbol(), ""));
		wGroupingSymbol.setText(Const.NVL(field.getGroupingSymbol(), ""));

		wNullAllowed.setSelection(field.isNullAllowed());
		wOnlyNullAllowed.setSelection(field.isOnlyNullAllowed());
		wOnlyNumeric.setSelection(field.isOnlyNumericAllowed());
		wMaxLength.setText(Const.NVL(field.getMaximumLength(), ""));
		wMinLength.setText(Const.NVL(field.getMinimumLength(), ""));
		wMaxValue.setText(Const.NVL(field.getMaximumValue(), ""));
		wMinValue.setText(Const.NVL(field.getMinimumValue(), ""));
		wStartStringExpected.setText(Const.NVL(field.getStartString(), ""));
		wEndStringExpected.setText(Const.NVL(field.getEndString(), ""));
		wStartStringDisallowed.setText(Const.NVL(field.getStartStringNotAllowed(), ""));
		wEndStringDisallowed.setText(Const.NVL(field.getEndStringNotAllowed(), ""));
		wRegExpExpected.setText(Const.NVL(field.getRegularExpression(), ""));
		wRegExpDisallowed.setText(Const.NVL(field.getRegularExpressionNotAllowed(), ""));

		wAllowedValues.removeAll();
		if (field.getAllowedValues() != null) {
			for (String allowedValue : field.getAllowedValues()) {
				wAllowedValues.add(Const.NVL(allowedValue, ""));
			}
		}
	}

	private void enableFields() {
		boolean visible = selectedField != null;

		wgType.setVisible(visible);
		wgData.setVisible(visible);

		wlFieldName.setVisible(visible);
		wFieldName.setVisible(visible);
		wlDescription.setVisible(visible);
		wDescription.setVisible(visible);
		wlErrorCode.setVisible(visible);
		wErrorCode.setVisible(visible);
		wlErrorDescription.setVisible(visible);
		wErrorDescription.setVisible(visible);

		boolean flag = true;
		wlAllowedValues.setEnabled(flag);
		wAllowedValues.setEnabled(flag);
		wbAddAllowed.setEnabled(flag);
		wbRemoveAllowed.setEnabled(flag);
	}

	private void refreshValidationsList() {
		wValidationsList.removeAll();
		for (Validation validation : selectionList) {
			wValidationsList.add(validation.getName());
		}
	}

	private void saveChanges() {
		if (selectedField != null) {
			// First grab the info from the dialog...
			selectedField.setFieldName(wFieldName.getText());
			selectedField.setErrorCode(wErrorCode.getText());
			selectedField.setErrorDescription(wErrorDescription.getText());
			selectedField.setDataTypeVerified(wDataTypeVerified.getSelection());
			selectedField.setDataType(ValueMeta.getType(wDataType.getText()));
			selectedField.setConversionMask(wConversionMask.getText());
			selectedField.setDecimalSymbol(wDecimalSymbol.getText());
			selectedField.setGroupingSymbol(wGroupingSymbol.getText());
			selectedField.setNullAllowed(wNullAllowed.getSelection());
			selectedField.setOnlyNullAllowed(wOnlyNullAllowed.getSelection());
			selectedField.setOnlyNumericAllowed(wOnlyNumeric.getSelection());
			selectedField.setMaximumLength(wMaxLength.getText());
			selectedField.setMinimumLength(wMinLength.getText());
			selectedField.setMaximumValue(wMaxValue.getText());
			selectedField.setMinimumValue(wMinValue.getText());
			selectedField.setStartString(wStartStringExpected.getText());
			selectedField.setEndString(wEndStringExpected.getText());
			selectedField.setStartStringNotAllowed(wStartStringDisallowed.getText());
			selectedField.setEndStringNotAllowed(wEndStringDisallowed.getText());
			selectedField.setRegularExpression(wRegExpExpected.getText());
			selectedField.setRegularExpressionNotAllowed(wRegExpDisallowed.getText());
			selectedField.setAllowedValues(wAllowedValues.getItems());
		}
	}

	private boolean getData() {
		// TODO
		if (Const.isEmpty(this.ruleFilePath)) {
			MessageBox msg = new MessageBox(shell, SWT.ICON_ERROR);
			msg.setText("Error");
			msg.setMessage("Invalide rule file path!");
			msg.open();
			return false;
		}
		File rule = new File(this.ruleFilePath);
		if (rule.exists()) {
			loadRules(rule);
		}

		// Populate the list of validations...
		refreshValidationsList();
		enableFields();
		return true;
	}

	private void loadRules(File rule) {
		SAXReader sax = new SAXReader();
		try {
			Document doc = sax.read(rule);
			Element root = doc.getRootElement();
			root.elements("");
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void ok() {
		if (Const.isEmpty(this.ruleFilePath)) {
			return;
		}
		saveChanges();
		writeRuleFile();
		shell.dispose();
	}

	private void writeRuleFile() {
		MessageBox msg = new MessageBox(shell);
		msg.setText("Import message!");
		msg.setMessage("Infos have been saved!");
		msg.open();
		// TODO Auto-generated method stub

	}

	private void cancel() {
		shell.dispose();
	}

	public static class ValueMeta {
		private static String[] types = new String[] { "None", "Number", "String", "Date", "Integer", "Internet Address" };

		public static String[] getTypes() {
			return types;
		}

		public static int getType(String text) {
			for (int i = 0; i < types.length; i++) {
				if (types[i].equals(text)) {
					return i;
				}
			}
			return 0;
		}

		public static String getTypeDesc(int dataType) {
			if (dataType < 0 || dataType >= types.length) {
				dataType = 0;
			}
			return types[dataType];
		}
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
				CustomDialog dia = new CustomDialog(new Shell(), "/tmp/rule.xml");
				dia.open();
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
