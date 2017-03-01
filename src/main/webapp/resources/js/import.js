var Import = function() {
    var globalSet = function() {
        toastr.options = {
            positionClass: 'toast-bottom-right',
            closeButton: true,
            timeOut: 5000,
        }
    }

    var checkInput = function() {
        var $this = $('#file');
        if ($this.val() == '') {
            return false;
        }
        var nReg = (/\.xml$/i);
        var isXML = nReg.test($this.val());
        return isXML;
    }
    var handleUploadFile = function() {
        //点击上传图片，并显示
        $('.fileinput').on('change.bs.fileinput', function() {
            var isXML = checkInput();
            if (isXML) {
                //alert('do something');
            } else if (isXML == false) {
                //toastr.error('Only support XML format');
                $(this).fileinput('clear');
            }
        })
    }

    return {
        init: function() {
            globalSet();
            handleUploadFile();
            jQuery('[data-toggle="popover"]').popover({"html":true});
        }
    }
}()
