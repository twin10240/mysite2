package com.bigdata2017.mysite.action.board;

import com.bigdata2017.web.Action;
import com.bigdata2017.web.ActionFactory;

public class BoardActionFactory extends ActionFactory{

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if( "writeform".equals( actionName ) ) {
			action = new BoardWirteFormAction();
	 	} else if("write".equals( actionName )) {
	 		action = new BoardWirteAction();
	 	} else if("view".equals( actionName )) {
	 		action = new BoardViewAction();
	 	} else if("modifyForm".equals( actionName )) {
	 		action = new BoardModifyFormAction();
	 	} else if("modify".equals( actionName )) {
	 		action = new BoardModifyAction();
	 	} else if("delete".equals( actionName )) {
	 		action = new BoarddeleteAction();
	 	} else {
	 		action = new BoardListAction();
	 	}
		
		return action;
	}

}
