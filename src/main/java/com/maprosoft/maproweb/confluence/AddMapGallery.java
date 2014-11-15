package com.maprosoft.maproweb.confluence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import com.atlassian.confluence.content.render.xhtml.ConversionContext;
import com.atlassian.confluence.content.render.xhtml.XhtmlException;
import com.atlassian.confluence.macro.Macro;
import com.atlassian.confluence.macro.MacroExecutionException;
import com.atlassian.confluence.xhtml.api.MacroDefinition;
import com.atlassian.confluence.xhtml.api.MacroDefinitionHandler;
import com.atlassian.confluence.xhtml.api.XhtmlContent;

public class AddMapGallery implements MaprowebAtlasConstants, Macro {
	
	private final XhtmlContent xhtmlUtils;

	public AddMapGallery(
			final XhtmlContent xhtmlUtils) {
		this.xhtmlUtils = xhtmlUtils;
	}

	@Override
	public String execute(
			final Map<String, String> parameters,
			final String bodyContent,
			final ConversionContext conversionContext)
	throws MacroExecutionException {
		String body = conversionContext.getEntity().getBodyAsString();

		final List<MacroDefinition> macros = new ArrayList<MacroDefinition>();

		try {
			xhtmlUtils.handleMacroDefinitions(body, conversionContext,
					new MacroDefinitionHandler() {
				@Override
				public void handle(MacroDefinition macroDefinition) {
					macros.add(macroDefinition);
				}
			});
		} catch (XhtmlException e) {
			throw new MacroExecutionException(e);
		}

		StringBuilder builder = new StringBuilder();
		
		String url = parameters.get("url");
		
		if (url == null || url.length() == 0) {
			builder.append("A URL must be provided");
		} else {
			// http://www.maprosoft.com/api-reference.html
			String width = MaprowebAtlasUtil.getParameter("width", parameters, "100%");
			String height = MaprowebAtlasUtil.getParameter("height", parameters, "500");
			
			StringBuilder iframeUrl = new StringBuilder();
			if (!url.startsWith("http://") && !url.startsWith("https://")) {
				iframeUrl.append("http://");
			}
			iframeUrl.append(url);
			iframeUrl.append("/map-gallery");
			
			AtomicReference<String> nextSeparator = new AtomicReference<String>("?");
			
//			MaprowebAtlasUtil.addParameter("fullscreenEnabled", "false", iframeUrl, nextSeparator, "&");
			
			// showMapToolbar=no&amp;showAllFeatureTypes=no&amp;
			
//			if (featureType != null) {
//				iframeUrl.append(nextSeparator);
//				iframeUrl.append("showFeatureTypes");
//				iframeUrl.append("=");
//				iframeUrl.append(featureType);
//				nextSeparator = AMP;
//			}

			// https://developer.mozilla.org/en/docs/Web/HTML/Element/iframe
			builder.append("<iframe src=\"").append(iframeUrl.toString()).append("\"\n");
			builder.append(" width=\"").append(width).append("\"\n");
			builder.append(" height=\"").append(height).append("\"\n");
			builder.append(" frameborder=\"0\" allowfullscreen=\"true\"\n");
			builder.append(">\n");
			builder.append("<p>Your browser does not support iframes</p>\n");
			builder.append("</iframe>\n");
		}
		
		return builder.toString();

	}

	@Override
	public BodyType getBodyType() {
		return BodyType.NONE;
	}

	@Override
	public OutputType getOutputType() {
		return OutputType.BLOCK;
	}

}
