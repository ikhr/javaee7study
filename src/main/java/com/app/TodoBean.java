package com.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "todoBean")
@ViewScoped
public class TodoBean implements Serializable {
	private String todo;
	private List<String> todoList;

	@PostConstruct
	public void init() {
		/*
		 * memo
		 * http://localhost:8080/myapp/
		 *
		 * GrashFish admin console
		 * http://localhost:4848
		 *
		 * glass fish 起動
		 * /Users/hikeda/Documents/dev/glassfish4/glassfish/bin/startserv
		 *
		 * glass fish へデプロイ
		 * pom.xmlでビルド
		 * /Users/hikeda/Documents/dev/glassfish4/glassfish/bin/asadmin deploy --force=true /Applications/Eclipse_4.7.0.app/Contents/workspace/myapp/target/myapp.war
		 *
		 */
		todoList = new ArrayList<>();

		// コンポーネントツリーを取得してクラス名を表示。
		UIViewRoot root = FacesContext.getCurrentInstance().getViewRoot();
		System.out.println(root.getClass().getName());

		// コンポーネントツリーを再帰的に表示する。
		viewTree(root.getChildren(), 1);

		printTree(root);
	}

	public void add() {
		todoList.add(todo);
	}

	public String getTodo() {
		return todo;
	}

	public void setTodo(String todo) {
		this.todo = todo;
	}

	public List<String> getTodoList() {
		return todoList;
	}

	public void setTodoList(List<String> todoList) {
		this.todoList = todoList;
	}

	/**
	 * コンポーネントツリーを標準出力する
	 *
	 * @param children
	 *            UIcomponent
	 * @param depth
	 *            1を指定。再帰処理で使用。
	 */
	private void viewTree(List<UIComponent> children, int depth) {
		children.stream().forEach(c -> {
			// ツリーの深さに合わせてコンポーネントのクラス名を表示する
			System.out.println(
					Stream.generate(() -> "-").limit(depth).collect(Collectors.joining()) + c.getClass().getName());
			if (c.getChildCount() > 0) {
				viewTree(c.getChildren(), depth + 1);
			}
		});
	}

	private void printTree(UIViewRoot root) {
		UIComponent lblComponent = root.findComponent("lbl");
		if (lblComponent != null && lblComponent instanceof HtmlOutputLabel) {
			System.out.println(((HtmlOutputLabel)lblComponent).getValue());
		}

	}
}
