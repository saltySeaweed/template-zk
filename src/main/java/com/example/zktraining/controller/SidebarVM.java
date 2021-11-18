package com.example.zktraining.controller;

import com.example.zktraining.ZkTrainingApplication;
import com.example.zktraining.dto.MenuDTO;
import com.example.zktraining.dto.NavigationDTO;
import com.example.zktraining.dto.TabInfo;
import com.example.zktraining.helper.AppConst;
import com.example.zktraining.services.FunctionService;
import com.example.zktraining.services.auth.HttpSessionService;
import org.springframework.util.StringUtils;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Tabbox;

import javax.servlet.http.HttpSession;
import java.util.List;

@VariableResolver(DelegatingVariableResolver.class)
public class SidebarVM {
	@WireVariable
	private FunctionService menusService;
	@WireVariable
	private HttpSessionService httpSessionService;

	private NavigationDTO navigationModel;
	private String userName = "Admin";
	private List<MenuDTO> menuList;
	private boolean collapsed = false;

	private String tackClass = AppConst.STRING.UNTACKED;

	protected ListModelList<TabInfo> tabInfos;
	protected TabInfo selected;

	private String logo;

	@Init
	public void init(@ScopeParam(AppConst.NAVIGATION) NavigationDTO navModel,
			@ScopeParam(AppConst.TABBOXES) ListModelList<TabInfo> tabInfosModel) {
		navigationModel = navModel;
		menuList = menusService.menus;
		MenuDTO menu = ZkTrainingApplication.ctx.getBean(FunctionService.class).findById(1);
		Tabbox tabbox = new Tabbox();
		tabInfos = tabInfosModel;

		logo = "";

		navigate(menu,tabbox);
	}

	@Command
	public void navigate(@BindingParam("menu") MenuDTO menu, @BindingParam("tbox") Tabbox tbox) {
		String targetPath = menu.getFunctionPath();
		if (!StringUtils.isEmpty(targetPath) && !targetPath.equals(NavigationDTO.BLANK_ZUL)) {
			navigationModel.setContentUrl(targetPath);
			BindUtils.postNotifyChange(null, null, navigationModel, "contentUrl");
		}

		if (menu != null && tabInfos != null && menu.getFunctionPath() != null && !"".equals(menu.getFunctionPath())) {
			TabInfo curentTab = new TabInfo(menu.getFunctionId(), menu.getFunctionCode(), menu.getFunctionPath(),
					menu.getFontIcon());
			boolean hasThisTab = false;
			for (int i = 0; i < tabInfos.size(); ++i) {
				TabInfo itemTabInfos = tabInfos.get(i);
				if (itemTabInfos.getId() != null && itemTabInfos.getId().equals(menu.getFunctionId())) {
					// da mo tab nay
					hasThisTab = true;
					itemTabInfos.setSelected(true);
					tabInfos.get(i).setSelected(true);
					tabInfos.get(i).setIconsclass("z-icon-thumb-tack");
					tabInfos.get(i).setId(menu.getFunctionId());
					tbox.setSelectedIndex(i);
				} else {
					tabInfos.get(i).setSelected(false);
				}
			}
			curentTab.setSelected(true);
			if (!hasThisTab) {
				curentTab.setIndexTab(tabInfos.size());
				tabInfos.add(curentTab);
			}

			HttpSession session = (HttpSession) (Executions.getCurrent()).getDesktop().getSession().getNativeSession();
			session.setAttribute("previousTabID", session.getAttribute("currentTabID"));
			session.setAttribute("currentTabID", menu.getFunctionId());

			// START - ADD DESKTOP TO MEMORY - REMOTE LOGOUT
			Desktop desktop = Executions.getCurrent().getDesktop();
			desktop.enableServerPush(true);
			httpSessionService.getDesktopMap().put(session.getId(), desktop);
			// END - ADD DESKTOP TO MEMORY - REMOTE LOGOUT
		} else {
			Clients.showNotification(Labels.getLabel("app.common.error"), "error", null, "after_end", 8000, true);
		}
		Sessions.getCurrent().setAttribute("tabInfos", tabInfos);
	}
	
	@NotifyChange({ "tackClass" })
	@Command
	public void tack() {
		if(AppConst.STRING.UNTACKED.equals(tackClass))
			tackClass = AppConst.STRING.TACKED;
		else
			tackClass = AppConst.STRING.UNTACKED;
	}

	@MatchMedia("all and (min-width: 958px)")
	@NotifyChange("collapsed")
	public void beWide() {
		collapsed = false;
	}

	@MatchMedia("all and (max-width: 957px)")
	@NotifyChange("collapsed")
	public void beNarrow() {
		collapsed = true;
	}

	public String getUserName() {
		return userName;
	}

	public List<MenuDTO> getMenuList() {
		return menuList;
	}

	public boolean isCollapsed() {
		return collapsed;
	}

	public void setCollapsed(boolean collapsed) {
		this.collapsed = collapsed;
	}

	public NavigationDTO getNavigationModel() {
		return navigationModel;
	}

	public String getLogo() {
		return logo;
	}
	
	public String getTackClass() {
		return tackClass;
	}
	
	public void setTackClass(String tackClass) {
		this.tackClass = tackClass;
	}
}
