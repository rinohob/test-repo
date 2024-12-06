(function ($) {
    "use strict";

    var _ = window._,
        Class = window.Class,
        CUI = window.CUI,
        RTE_LINK_DIALOG = "rtelinkdialog";

    if(CUI.rte.ui.cui.CuiDialogHelper.aemExtended){
        return;
    }

    var AEMLinkBaseDialog = new Class({
        extend: CUI.rte.ui.cui.CQLinkBaseDialog,

        toString: "AEMLinkBaseDialog",

        initialize: function(config) {
            this.superClass.initialize.call(this, config);

            this.$rteDialog = this.$container.find("[data-rte-dialog=link]");

            this.$rteDialog.find("coral-select-item:last").after(getOptionHtml());
            
            var modalPicker = this.$rteDialog.find("foundation-autocomplete");
            
            modalPicker.attr('pickersrc',modalPicker.attr('pickersrc').replace("{value}", "?exclude=none{value}"));
        },

        dlgToModel: function() {
			var targetSelectField =  this.$rteDialog.find("coral-select").find("coral-select-item:selected");

			if(targetSelectField.length === 0){
                this.superClass.dlgToModel.call(this);
				return;
			}
			var targetval = targetSelectField.val();
			if (targetval.length === 0 || targetval != '_modal') {
				this.objToEdit.attributes["class"] = "";
                this.superClass.dlgToModel.call(this);
				return;
			}
			this.objToEdit.attributes["class"] = 'modalpopup';
            targetSelectField.parent().val('');
            this.superClass.dlgToModel.call(this);
        },

        dlgFromModel: function() {
            if (_.isEmpty(this.objToEdit) || _.isEmpty(this.objToEdit.href) || this.objToEdit.attributes.class != 'modalpopup') {
                this.superClass.dlgFromModel.call(this);
                return;
                
            }
			this.superClass.dlgFromModel.call(this);
			var $target = this.$rteDialog.find("coral-select").find("coral-select-item[value='_modal']").parent();
			if(this.objToEdit.attributes.class == 'modalpopup'){
				$target.val('_modal');
			}else{
               this.superClass.dlgFromModel.call(this); 
            }
           
        }
    });

    CUI.rte.ui.cui.CuiDialogHelper = new Class({
        extend: CUI.rte.ui.cui.CuiDialogHelper,

        toString: "AEMCuiDialogHelper",

        instantiateDialog: function(dialogConfig) {
            var type = dialogConfig.type;

            if(type !== RTE_LINK_DIALOG){
                this.superClass.instantiateDialog.call(this, dialogConfig);
                return;
            }

            var $editable = $(this.editorKernel.getEditContext().root),
                $container = CUI.rte.UIUtils.getUIContainer($editable),
                dialog = new AEMLinkBaseDialog();

            dialog.attach(dialogConfig, $container, this.editorKernel);

            return dialog;
        }
    });

    function getOptionHtml(){
        return "<coral-select-item value='_modal'>Modal</coral-select-item>";
    }

    CUI.rte.ui.cui.CuiDialogHelper.aemExtended = true;
})(jQuery);