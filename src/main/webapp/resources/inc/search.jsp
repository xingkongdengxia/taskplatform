<%@ page contentType="text/html; charset=utf-8"%>


<div class="panel panel-default">
    <div class="panel-heading">
        <a data-toggle="collapse" data-parent="#main" href="#collapseone">
            查询条件(点击折叠)
        </a> 
    </div>
    <div id="collapseone" class="panel-collapse collapse in">
        <div class="panel-body">
            <form id="formSearch" class="form-horizontal">
                <div class="form-group" style="margin-top:15px">
                    <div class="col-sm-3">
                        <label class="control-label" for="txt_search_key">关键字查询</label>
                        <input type="text" class="form-control" id="txt_search_key">
                    </div>                            
                    <div class="col-sm-4" style="text-align:left;">
                        <button type="button" style="margin-left:50px" id="btn_query" class="btn btn-primary" onclick="search()">查询</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>