package com.example.zktraining.controller;

import com.example.zktraining.dto.NavigationDTO;
import com.example.zktraining.dto.TabInfo;
import com.example.zktraining.helper.AppConst;
import com.example.zktraining.helper.Utils;
import lombok.Getter;
import lombok.Setter;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.*;
import org.zkoss.zul.theme.Themes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@VariableResolver(DelegatingVariableResolver.class)
public class MainVM {
	@Wire("#tbox")
	public Tabbox tbox;

	private NavigationDTO navigationModel;

	@Setter
	@Getter
	private ListModelList<TabInfo> tabInfos;
	@Getter
	@Setter
	private TabInfo selected;

	public final static String ACTIVE_THEME = "active.theme";
	@Getter
	@Setter
	private String langSelected = AppConst.STRING.DFLANG;
	private String imageResources = AppConst.Language.IMAGE_RESOURCES;
	public String imageLangSelected = AppConst.Language.US;
	@Getter
	@Setter
	private String themeSelected = AppConst.STRING.DFTHEME;
	private String logo;

	private String footerApp;

	@Init
	public void init(@ContextParam(ContextType.DESKTOP) Desktop desktop) {
		initNav(desktop);
		initTabs(desktop);
		initLang();
		initTheme();
		initTitle();

		HttpSession session = (HttpSession) desktop.getSession().getNativeSession();
		desktop.enableServerPush(true);
		logo = "/resources/images/ems.png";
	}

	private void initLang() {
		String lang = (String) Sessions.getCurrent().getAttribute(AppConst.Language.ACTIVE_LANG);
		if (lang != null) {
			langSelected = lang;
		}
		Utils.refreshLang(langSelected);
		imageLangSelected = (String) Sessions.getCurrent().getAttribute(AppConst.Language.ACTIVE_LANG_IMAGE);
	}

	private void initTheme() {
		String theme = (String) Sessions.getCurrent().getAttribute(ACTIVE_THEME);
		if (theme != null)
			themeSelected = theme;
		else
			themeSelected = AppConst.STRING.DFTHEME;
	}

	private void initTitle() {

		Executions.getCurrent().getDesktop().getFirstPage().setTitle("Website bán hàng");
	}

	private void initNav(Desktop desktop) {
		navigationModel = new NavigationDTO();
		desktop.setAttribute(AppConst.NAVIGATION, navigationModel);
	}

	private void initTabs(Desktop desktop) {
		tabInfos = new ListModelList<>();
		desktop.setAttribute(AppConst.TABBOXES, tabInfos);
	}

	@NotifyChange({ "themeSelected" })
	@Command
	public void changeThemes(@BindingParam("type") String type) {
		themeSelected = type;
		Sessions.getCurrent().setAttribute(ACTIVE_THEME, themeSelected);
	}

	@Command
	public void changeTheme(@BindingParam("type") String type) {
		themeSelected = type;
		refeshTheme();
		Executions.sendRedirect(AppConst.STRING.EMPTY);
	}

	private void refeshTheme() {
		Themes.setTheme(Executions.getCurrent(), themeSelected);
		Sessions.getCurrent().setAttribute(ACTIVE_THEME, themeSelected);
	}

	@Command
	public void logout() {
		HttpSession session = (HttpSession) (Executions.getCurrent()).getDesktop().getSession().getNativeSession();
		if (session == null)
			return;
		// redirect to '/login' and destroyed session
	}

	@NotifyChange({ "langSelected" })
	@Command
	public void changeLang(@BindingParam("type") String type, @BindingParam("imageKey") String imageKey) {
		langSelected = type;
		if (imageKey != null)
			imageKey = imageKey.toLowerCase();
		imageLangSelected = imageKey;
		Utils.refreshLang(langSelected);
		Executions.sendRedirect(AppConst.STRING.EMPTY);
	}

	@Command
	public void viewNotification() {
		System.out.println("viewNotification");
	}

	@Command
	public void updateTabID(@BindingParam("id") Integer id) {
		HttpSession session = (HttpSession) (Executions.getCurrent()).getDesktop().getSession().getNativeSession();
		session.setAttribute("previousTabID", session.getAttribute("currentTabID"));
		session.setAttribute("currentTabID", id);
		for(TabInfo tab : tabInfos) {
			if(tab.getSelected()) {
				tab.setSelected(false);
			}
			if(id == tab.getId())
			{
				tab.setSelected(true);
			}
		}
	}

	@GlobalCommand
	@Command("closeTab")
	public void closeTab(@BindingParam("idTab") Integer idTab) {
		tabInfos.removeIf(e -> idTab.equals(e.getId()));
	}

	@GlobalCommand
	@Command("closeAllTabs")
	public void closeAllTabs() {
		List<Integer> pins = new ArrayList<Integer>();
		for (int i = 0; i < tabInfos.size(); i++) {
			if (!"z-icon-thumb-tack".equals(tabInfos.get(i).getIconsclass())) {
				pins.add(tabInfos.get(i).getId());
			}
		}
		tabInfos.removeIf(e -> Utils.contains(pins, e.getId()));
	}

	@GlobalCommand
	@Command("closeOtherTabs")
	public void closeOtherTabs(@BindingParam("idTab") Integer idTab) {
		if (tabInfos.size() == 1 || null == idTab) {
			return;
		}
		List<Integer> pins = new ArrayList<Integer>();
		for (int i = 0; i < tabInfos.size(); i++) {
			if (!"z-icon-thumb-tack".equals(tabInfos.get(i).getIconsclass())
					&& !idTab.equals(tabInfos.get(i).getId())) {
				pins.add(tabInfos.get(i).getId());
			}
		}
		tabInfos.removeIf(e -> Utils.contains(pins, e.getId()));
	}

	@GlobalCommand
	@Command("pinTab")
	public void pinTab(@BindingParam("idTab") Integer idTab) {
		for (int i = 0; i < tabInfos.size(); i++) {
			if (tabInfos.get(i).getId().equals(idTab)) {
				tabInfos.get(i).setIconsclass("z-icon-thumb-tack");
				BindUtils.postNotifyChange(null, null, tabInfos.get(i), "iconsclass");
			}
		}
	}

	public void setTabInfos(ListModelList<TabInfo> tabInfos) {
		this.tabInfos = tabInfos;
	}

	@GlobalCommand
	@Command("unpinTab")
	public void unpinTab(@BindingParam("idTab") Integer idTab) {
		for (int i = 0; i < tabInfos.size(); i++) {
			if (tabInfos.get(i).getId().equals(idTab)) {
				tabInfos.get(i).setIconsclass("");
				;
				BindUtils.postNotifyChange(null, null, tabInfos.get(i), "iconsclass");
			}
		}
	}

	private void showNotify(String msg) {
		Clients.showNotification(msg, "info", null, null, 1000);
	}

	@Command
	public void drop(@ContextParam(ContextType.TRIGGER_EVENT) DropEvent event) {
		Tab dragged = (Tab) event.getDragged();
		Tab target = (Tab) event.getTarget();
		if (dragged.getIndex() > target.getIndex()) {
			Tabpanel drag = dragged.getLinkedPanel();
			Tabpanel drop = target.getLinkedPanel();
			Tabpanels panels = (Tabpanels) drag.getParent();
			panels.getChildren().remove(drag);
			panels.getChildren().add(panels.getChildren().indexOf(drop), drag);
			dragged.getParent().insertBefore(dragged, target);
			dragged.setSelected(true);
			panels.invalidate();// strange behavior when I don't do this
		} else {
			Tabpanel drag = dragged.getLinkedPanel();
			Tabpanel drop = target.getLinkedPanel();
			Tabpanels panels = (Tabpanels) drag.getParent();
			panels.getChildren().remove(drag);
			panels.getChildren().add(panels.getChildren().indexOf(drop) + 1, drag);
			Tabs tabs = (Tabs) dragged.getParent();
			tabs.getChildren().remove(dragged);
			tabs.getChildren().add(tabs.getChildren().indexOf(target) + 1, dragged);
			dragged.setSelected(true);
			panels.invalidate(); // strange behavior when I don't do this
		}
	}

	public String getContentUrl() {
		return navigationModel.getContentUrl();
	}

	public NavigationDTO getNavigationModel() {
		return navigationModel;
	}

	public String getFooterApp() {
		return footerApp;
	}

	public String getImageResources() {
		return imageResources;
	}

	public String getImageLangSelected() {
		return imageLangSelected;
	}
}
