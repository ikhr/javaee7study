package com.app.flow;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.faces.flow.Flow;
import javax.faces.flow.builder.FlowBuilder;
import javax.faces.flow.builder.FlowBuilderParameter;
import javax.faces.flow.builder.FlowDefinition;
import javax.inject.Named;

@Named
@Dependent
public class FlowCounterDefinition {

	private static final String flowId="counterFlow";

	public String getFlowId() {
		return flowId;
	}

	@Produces
	@FlowDefinition
	public Flow defineFlow(@FlowBuilderParameter FlowBuilder flowBuilder) {
		// このフローにドキュメント定義IDを付与する場合、第一引数に文字列を指定する。必要なければ空文字。
		flowBuilder.id("", flowId);

		flowBuilder.initializer("#{flowCounter.init()}");
		flowBuilder.viewNode(flowId, "/flow/firstFlow.xhtml").markAsStartNode();
		flowBuilder.viewNode("second", "/flow/secondFlow.xhtml");
		flowBuilder.viewNode("third", "/flow/thirdFlow.xhtml");
		flowBuilder.returnNode("return").fromOutcome("/flow/flowEntry.xhtml");

		flowBuilder.finalizer("#{flowCounter.fin()}");
		return flowBuilder.getFlow();
	}
}
