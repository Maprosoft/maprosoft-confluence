package com.maprosoft.maproweb.confluence;

import java.util.ArrayList;
import java.util.LinkedList;
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

public class AddPopupMapLink implements MaprowebAtlasConstants, Macro {
	
	private final XhtmlContent xhtmlUtils;

	public AddPopupMapLink(XhtmlContent xhtmlUtils) {
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
		List<String> errorMessages = new LinkedList<String>();
		
		String url = parameters.get("maproweb-url");
		
		if (MaprowebAtlasUtil.isNil(url)) {
			url = MaprowebAtlasUtil.getParameter("site", parameters, null);
		}
		
		if (MaprowebAtlasUtil.isNil(url)) {
			errorMessages.add("A URL must be provided");
		}

		String linkContent = bodyContent;
		if (MaprowebAtlasUtil.isNil(linkContent)) {
			linkContent = "";
		}

		String focusFeatureType = MaprowebAtlasUtil.getParameter("focusFeatureType", parameters, null);
		if (MaprowebAtlasUtil.isNil(focusFeatureType)) {
			errorMessages.add("A feature type must be specified.");
		}
		
		String focusFeatureKey = MaprowebAtlasUtil.getParameter("focusFeatureKey", parameters, null);
		if (MaprowebAtlasUtil.isNil(focusFeatureKey)) {
			errorMessages.add("A feature key must be specified.");
		}
		
		if (errorMessages.size() == 0) {
			// http://www.maprosoft.com/api-reference.html
			//				String mapApi = "no";//MaprowebAtlasUtil.getParameter("mapApi", parameters, null);
			String showMapToolbar = "no";//MaprowebAtlasUtil.getParameter("showMapToolbar", parameters, "no");
			//				String showStackToolbar = "no";//MaprowebAtlasUtil.getParameter("showStackToolbar", parameters, "no");
			String showAllFeatureTypes = "no";//MaprowebAtlasUtil.getParameter("showAllFeatureTypes", parameters, "no");
			String showFeatureTypes = "no";//MaprowebAtlasUtil.getParameter("showFeatureTypes", parameters, null);
			String focusAction = MaprowebAtlasUtil.getParameter("focusAction", parameters, null);
			String tooltip = MaprowebAtlasUtil.getParameter("tooltip", parameters, "");

			StringBuilder iframeUrl = new StringBuilder();
			if (!url.startsWith("http://") && !url.startsWith("https://")) {
				iframeUrl.append("http://");
			}
			iframeUrl.append(url);

			String focusFeature = focusFeatureType + FEATURE_PARAM_VALUE_DELIMINATOR + focusFeatureKey;

			AtomicReference<String> nextSeparator = new AtomicReference<String>("?");
			//				if (mapApi != null) {
			//					MaprowebAtlasUtil.addParameter("mapApi", mapApi, iframeUrl, nextSeparator, "&");
			//				}
			MaprowebAtlasUtil.addParameter("focusFeature", focusFeature, iframeUrl, nextSeparator, "&");
			MaprowebAtlasUtil.addParameter("showMapToolbar", showMapToolbar, iframeUrl, nextSeparator, "&");
			MaprowebAtlasUtil.addParameter("showAllFeatureTypes", showAllFeatureTypes, iframeUrl, nextSeparator, "&");
			MaprowebAtlasUtil.addParameter("showFeatureTypes", showFeatureTypes, iframeUrl, nextSeparator, "&");
			
			if (!MaprowebAtlasUtil.isNil(focusAction)) {
				MaprowebAtlasUtil.addParameter("focusAction", focusAction, iframeUrl, nextSeparator, "&");
			}

			// The fullscreen button won't work if in a popup.
			MaprowebAtlasUtil.addParameter("fullscreenEnabled", "false", iframeUrl, nextSeparator, "&");

			// This parameter is required for the popup to work.
			MaprowebAtlasUtil.addParameter("iframe", "true", iframeUrl, nextSeparator, "&");

			builder.append("<a href=\"").append(iframeUrl.toString()).append("\"\n");
			builder.append(" title=\"").append(tooltip).append("\"\n");
			builder.append(" rel=\"mapPopup[iframes]\"\n");
			builder.append(">\n");
			builder.append(linkContent);
			builder.append("\n");
			builder.append("</a>\n");

			builder.append("<script>\n");
			builder.append("if (window.jQuery) {");
			builder.append("$(document).ready(function() {");
			builder.append("  if (prepareMapPopups) {\n");
			builder.append("    //alert('preparing map popup...');\n");
			builder.append("    prepareMapPopups();\n");
			builder.append("    //alert('prepared.');\n");
			builder.append("  } else {\n");
			builder.append("    //alert('prepareMapPopups does not exist.');\n");
			builder.append("  }\n");
			builder.append("});\n");
			builder.append("}\n");
			builder.append("</script>\n");

		}
		
		return builder.toString();

	}

//	@Override
//	public BodyType getBodyType() {
//		return BodyType.RICH_TEXT;
//	}
//
//	@Override
//	public OutputType getOutputType() {
//		return OutputType.INLINE;
//	}
	
//	@Override
//	public boolean hasBody() {
//	  return false;
//	}

	@Override
	public BodyType getBodyType() {
		return BodyType.RICH_TEXT;
	}

	@Override
	public OutputType getOutputType() {
		return OutputType.INLINE;
	}

}