function doSearch() {
	var operator = $("#operator").val();
	var fromTime = $("#fromTime").val();
	var toTime = $("#toTime").val();
	Logs.handleDT(operator, fromTime, toTime);
}

function doExport(id, sign) {
	id = id.replace("doc_", "");
	var inputId='<input type="hidden" name="id" value="'+ id+'" />';
	var inputSign='<input type="hidden" name="sign" value="'+ sign+'" />';
	jQuery('<form action="downloadLogDoc.do" method="post">'+inputId+inputSign+'</form>')
    .appendTo('body').submit().remove();
}

function doRecovery(id){
	bootbox.confirm("Are you sure to recovery the service?", function(result){
		if(result){
			$.blockUI({ message: '<h5><img src="../resources/images/loading.gif" /> Processing...</h5>' });
			$.post("recovery.do", {
				"id": id
			}, function(data, status){
				$.unblockUI();
				if(data=="success"){
					bootbox.alert("Recovery success!");
				}else{
					bootbox.alert(data);
				}
				doSearch();
			});
		}
	})
}

var Logs = function() {

    var globalSet = function() {
        toastr.options = {
            positionClass: 'toast-bottom-right',
            closeButton: true,
            timeOut: 2000,
        }
    }
    
    var handleDatePickers = function(model) {
        var options = {};
        if (model == 'daily') {
            options = {
                autoclose: true,
                minViewMode: 'year',
                format: 'yyyy-mm-dd'
            }
        } else if (model == 'monthly') {
            options = {
                autoclose: true,
                minViewMode: 'months',
                format: 'yyyy-mm'
            }
        } else {
            options = {
                autoclose: true,
                minViewMode: 'year',
                format: 'yyyy-mm-dd'
            }
        }
        if (jQuery().datepicker) {
            $('.date-picker').datepicker(options);
        }
    }
       //传入的是叶节点的id，根据id生成表格数据
    var oTable; 
    var handleDT = function(operator, fromTime, toTime) {
		allSelectItem = new Array();
		if(operator == undefined) operator = '';
		if(fromTime == undefined) fromTime = '';
		if(toTime == undefined) toTime = '';
        if ($('.dataTable').size() > 0) {
            oTable.api().ajax.url("queryLogsPage.do?" + "operator="+operator + "&fromTime="+fromTime + "&toTime="+toTime).load();
        } else {
            oTable = $('#logs').dataTable({
                "processing": true,
                "lengthMenu": [
                    [8, 25, 50, -1],
                    [8, 25, 50, "All"]
                ],
                "ajax": "queryLogsPage.do",
                "columns": [{
                    "data": 'operateUser',
                }, {
					"data" : 'operateTime'
				}, {
					"data" : 'ip'
				}, {
					"data" : 'old_doc'
				}, {
					"data" : 'new_doc'
				}, {
					"data" : 'id'
				}],
                "columnDefs": [{
                    targets: 3,
                    width: "19px",
                    render: function(data, type, full, meta) {
                    	if(data==null || data==""){
                    		return "-";
                    	}
                        var html = '<a id="doc_' + full.id + '" style="cursor:pointer" onclick="doExport(this.id,\'old\')">old_doc</a>'
                        return html;
                    },
                }, {
                    targets: 4,
                    width: "19px",
                    render: function(data, type, full, meta) {
                        var html = '<a id="doc_' + full.id + '" style="cursor:pointer" onclick="doExport(this.id,\'new\')">new_doc</a>'
                        return html;
                    },
                }, {
                    targets: 5,
                    width: "19px",
                    render: function(data, type, full, meta) {
                    	if(full.old_doc==null || full.old_doc=="" || full.status=="R"){
                    		return "-";
                    	}
                        var html = "<a style=\"cursor:pointer\" onclick=\"doRecovery("+ full.id +")\">recovery</a>";
                        return html;
                    },
                }],
                "order": [
                    [1, 'desc']
                ],
                "drawCallback": function(settings) {},
                "preDrawCallback": function( settings, aData ) {}
            });
            jQuery('#dt_filter').hide();
            jQuery('#dt_wrapper .dataTables_length select').addClass("form-control input-small"); // modify table per page dropdown
            
        }
        handleDatePickers();
    }
    return {
        init: function() {
            globalSet();
            handleDT(); //初始化的参数为空 则返回空数据，待选择树菜单后再往表格内填充数据
        },
        handleDT:handleDT,
    }
}()
