var Export = function() {
	var globalSet = function() {
	    toastr.options = {
	        positionClass: 'toast-bottom-right',
	        closeButton: true,
	        timeOut: 2000,
	    }
	};
     //checkbox美化
    function handleUniform() {
        if (!jQuery().uniform) {
            return;
        }
        var test = $("input[type=checkbox]:not(.toggle), input[type=radio]:not(.toggle, .star)");
    	
        if (test.size() > 0) {
            test.each(function() {
                if ($(this).parents(".checker").size() == 0) {
                    $(this).show();
                    $(this).uniform();
                }
            });
        }
    };
    //全选功能
    var handleCheckBox = function() {
        $('.group-checkable').change(function() {
            var set = jQuery(this).attr("data-set");
            var checked = jQuery(this).is(":checked");
            $(set).each(function() {
                if (checked) {
                    this.checked = true;
                    if(allSelectItem.length == 0) {
                    	allSelectItem.push($(this).val());
                    } else {
                        var objVal = $(this).val();
                        var contain = false;
                        $.each(allSelectItem,function(index, value) {
                			if(objVal == value) {
                				contain = true;
                			}
                		});
                        if(!contain) {
                        	allSelectItem.push(objVal);
                        }
                    }
                    $(this).parents('tr').addClass("active");
                } else {
                    this.checked = false;
                    $(this).parents('tr').removeClass("active");
                    var objVal = $(this).val();
                    $.each(allSelectItem,function(index, value) {
            			if(objVal == value) {
            				allSelectItem.splice($.inArray(value,allSelectItem),1);
            				return;
            			}
            		});
                }
            });
            $.uniform.update(set);
        });
        $('.group-check').on('change', 'tbody tr .checkboxes',function() {
            $(this).parents('tr').toggleClass("active");
        });
    }
//传入的是叶节点的id，根据id生成表格数据
var oTable; 
var handleDT = function(serviceName, style) {
		allSelectItem = new Array();
		if(serviceName == undefined) serviceName = '';
		if(style == undefined) style = '';
        if ($('.dataTable').size() > 0) {
            oTable.api().ajax.url("queryServicePage.do?" + "name="+serviceName + "&style="+style).load();
        } else {
            oTable = $('#export').dataTable({
            	//"scrollX": true,
                "processing": true,
                "lengthMenu": [
                    [8, 25, 50, -1],
                    [8, 25, 50, "All"]
                ],
                "ajax": "queryServicePage.do",
                "columns": [{
                    "orderable": false,
                    "data": null,
                }, {
					"data" : 'serviceCode'
				}, {
					"data" : 'style'
				}, {
					"data" : 'name'
				}, {
					"data" : 'modifyTime'
				}, {
					"data" : 'serviceAddress'
				}, {
					"data" : 'status'
				}],
                columnDefs: [{
                        targets: 0,
                        width: "19px",
                        render: function(data, type, full, meta) {
                            var html = '<input type="checkbox" name="" class="checkboxes" value="' + full.serviceCode + '">'
                            return html;
                        }
                    },{
                        targets: 1,
                        render: function(data, type, full, meta) {
                        	if(data.length>30){
                        		return ("<a data-toggle='popover' data-trigger='hover' data-placement='top' title='"+data+"'>" + (data.substring(0, 29) + "...")+"</a>");
                        	}
                        	return data;
                        }
                    },{
                        targets: 2,
                        render: function(data, type, full, meta) {
                            if(data=="1"){
                            	return "rest";
                            }else if(data=="2"){
                            	return "webservice";
                            }else if(data=="4"){
                            	return "http";
                            }else{
                            	return "file";
                        	}
                        }
                    },{
                        targets: 3,
                        render: function(data, type, full, meta) {
                        	if(data.length>30){
                        		return ("<a data-toggle='popover' data-trigger='hover' data-placement='top' title='"+data+"'>" + (data.substring(0, 29) + "...")+"</a>");
                        	}
                        	return data;
                        }
                    }
                ],
                "order": [
                    [1, 'asc']
                ],
                "drawCallback": function(settings) {
                    handleUniform();
                    var cur = this.api().page();
                    var state = parseInt($('.group-checkable').attr('page' + cur));
                    if (state) {
                        $('.group-checkable').get(0).checked = true;
                    } else {
                        $('.group-checkable').get(0).checked = false;
                    }
                    $.uniform.update('.group-checkable');

                }
            });
//                    jQuery('#dt_wrapper .dataTables_filter input').addClass("form-control input-small input-inline"); // modify table search input
            jQuery('#dt_filter').hide();
            jQuery('#dt_wrapper .dataTables_length select').addClass("form-control input-small"); // modify table per page dropdown
            //记录每一页的全选按钮的状态
            $('.group-checkable').bind('click', function() {
                var checked = $(this).is(':checked');
                var cur = oTable.api().page();
                if (checked) {
                    $(this).attr('page' + cur, 1);
                } else {
                    $(this).attr('page' + cur, 0);
                }
            })
        }
    };
    return {
        init: function() {
            globalSet();
            handleUniform();
            handleCheckBox();
            handleDT(); //初始化的参数为空 则返回空数据，待选择树菜单后再往表格内填充数据
            $('#export').css({'width':'100%'});
            $(document).on('click','.checkboxes',function(e){
            	var tar = e.target;
            	if(tar.checked == true) {
            		allSelectItem.push(tar.value);
            	} else {
            		$.each(allSelectItem,function(index, value) {
            			if(tar.value == value) {
            				allSelectItem.splice($.inArray(value,allSelectItem),1);
            				return;
            			}
            		});
            	}
            })
        },
        handleDT:handleDT,
    }
}()