maprosoft-confluence
====================

# Introduction

This add-on provides integration between Confluence and Maprosoft's products - [Maproweb](http://www.maprosoft.com/maproweb.html), [Maproapp](http://www.maprosoft.com/maproapp.html) and [Maproissue](http://www.maprosoft.com/maproissue.html).

Currently the add-on provides macros to enable maps to be easily added to Confluence pages. Further integrations and enhancements are planned.

# Running

Install Atlassian's Plugin SDK as per the instructions on their website:

https://developer.atlassian.com/docs

Here are the main SDK commands you'll use to contribute to this add-on:

* atlas-compile -- builds the project
* atlas-run     -- installs this plugin into the product and starts it on localhost
* atlas-debug   -- same as atlas-run, but allows a debugger to attach at port 5005
* atlas-cli     -- after atlas-run or atlas-debug, opens a Maven command line window:
                   - 'pi' reinstalls the plugin into the running product instance
* atlas-help    -- prints description for all commands in the SDK


# Support

for support, please [contact Maprosoft](http://www.maprosoft.com/contact.html).


# Online Help

The Maprosoft Confluence plugin provides convenient macros to help embed Maprosoft maps in Confluence. Each of the macros contain fields to fill in, many of which are named so that they are consistent with [Maprosoft's API](http://www.maprosoft.com/api-reference.html).

## Adding a Map

The 'Add Map' macro allows a [Maproweb](http://www.maprosoft.com/maproweb.html) to be embedded within a Confluence page.

Fill in the macro fields as follows:

* url: This is the URL where [Maproweb](http://www.maprosoft.com/maproweb.html) is running.
* mapApi: Select the type of map system to use - GoogleMaps or MapQuest.
* showMapToolbar: Indicates whether to show a toolbar allowing the control of certain features. [More info](http://www.maprosoft.com/api-reference.html#showMapToolbar).
* showStackToolbar: This only has effect if showMapToolbar is true. If this is set to true, then the 'Stack Toolbar' will be chosen, otherwise a regular toolbar will be chosen. [More info](http://www.maprosoft.com/api-reference.html#showStackToolbar).
* showAllFeatureTypes: Indicates whether all feature types should be made visible. If showMapToolbar is false, then this will result in all map data being downloaded. [More info](http://www.maprosoft.com/api-reference.html#showAllFeatureTypes).
* showFeatureTypes: Indicates specific feature types to make available to the map. [More info](http://www.maprosoft.com/api-reference.html#showFeatureTypes).
* width: the width of the map. Use HTML notation (e.g. 600px, 100%).
* height: the height of the map. Use HTML notation.


## Adding a Popup Map

The 'Add Map Popup' macro allows a link to be created that, when clicked, results in a [Maproweb](http://www.maprosoft.com/maproweb.html) map popping up.

Fill in the macro fields as follows:

* url: This is the URL where [Maproweb](http://www.maprosoft.com/maproweb.html) is running.
* mapApi: Select the type of map system to use - GoogleMaps or MapQuest.
* tooltip: A tooltip to display when the cursor hovers over the link. [More info](http://www.maprosoft.com/api-reference.html#tooltip).
* focusFeatureType: If the map should initially focus on an item within it, then this parameter specifies the type of the feature. [More info](http://www.maprosoft.com/api-reference.html#focusFeatureType).
* focusFeatureKey: If the map should initially focus on an item within it, then this parameter specifies the feature's key. [More info](http://www.maprosoft.com/api-reference.html#focusFeatureKey).
* focusAction: If the map should initially focus on an item within it, then this parameter specifies the type of focus action to occur. [More info](http://www.maprosoft.com/api-reference.html#focusAction).


## Adding a Map Gallery

The 'Add Map Gallery' macro allows a [Maproweb map gallery](http://www.maprosoft.com/map-gallery.html) to be embedded within a Confluence page.

Fill in the macro fields as follows:

* url: This is the URL where [Maproweb](http://www.maprosoft.com/maproweb.html) is running.
* width: the width of the map gallery. Use HTML notation (e.g. 600px, 100%).
* height: the height of the map gallery. Use HTML notation.



