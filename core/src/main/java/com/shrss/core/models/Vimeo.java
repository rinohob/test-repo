package com.shrss.core.models;

import java.net.URISyntaxException;

import org.jetbrains.annotations.Nullable;
//import javax.annotation.*;

import com.adobe.cq.wcm.core.components.models.embeddable.Embeddable;

public interface Vimeo extends Embeddable {

	/**
	 * Name of the resource property that defines the id of the Vimeo video.
	 */
	String PN_VIDEO_ID = "vimeoVideoId";

	/**
	 * Name of the resource property that defines the width of the iFrame hosting
	 * the Vimeo video.
	 */
	String PN_WIDTH = "vimeoWidth";

	/**
	 * Name of the resource property that defines the height of the iFrame hosting
	 * the Vimeo video.
	 */
	String PN_HEIGHT = "vimeoHeight";

	/**
	 * Name of the resource property that defines the aspect ratio of the iFrame
	 * hosting the Vimeo video.
	 */
	String PN_ASPECT_RATIO = "vimeoAspectRatio";

	/**
	 * Name of the resource property that defines the layout type of the Vimeo
	 * video.
	 */
	String PN_LAYOUT = "vimeoLayout";

	/*
	 * The following resource property names are used for optional Vimeo player
	 * parameters
	 */
	String PN_AUTOPLAY = "vimeoAutoPlay";

	String PN_MUTE = "vimeoMute";

	String PN_LOOP = "vimeoLoop";

	String PN_REL = "vimeoRel";

	String PN_ACCESSIBILITY_LABEL = "vimeoAccessibilityLabel";

	String PN_PLAYS_INLINE = "vimeoPlaysInline";

	String PN_DESIGN_MUTE_ENABLED = "vimeoMuteEnabled";

	String PN_DESIGN_MUTE_DEFAULT_VALUE = "vimeoMuteDefaultValue";

	String PN_DESIGN_AUTOPLAY_ENABLED = "vimeoAutoPlayEnabled";

	String PN_DESIGN_AUTOPLAY_DEFAULT_VALUE = "vimeoAutoPlayDefaultValue";

	String PN_DESIGN_LOOP_ENABLED = "vimeoLoopEnabled";

	String PN_DESIGN_LOOP_DEFAULT_VALUE = "vimeoLoopDefaultValue";

	String PN_DESIGN_RELATED_VIDEOS_ENABLED = "vimeoRelatedVideosEnabled";

	String PN_DESIGN_RELATED_VIDEOS_DEFAULT_VALUE = "vimeoRelatedVideosDefaultValue";

	String PN_DESIGN_PLAYS_INLINE_ENABLED = "vimeoPlaysInlineEnabled";

	String PN_DESIGN_PLAYS_INLINE_DEFAULT_VALUE = "vimeoPlaysInlineDefaultValue";

	/**
	 * Gets the accessibilityLabel
	 * 
	 * @return accessibilityLabel
	 */
	default @Nullable String getVimeoAccessibilityLabel() {
		return "Vimeo Video";
	}

	/**
	 * Gets the width
	 * 
	 * @return width
	 */
	default @Nullable String getIFrameWidth() {
		return null;
	}

	/**
	 * Gets the height
	 * 
	 * @return height
	 */
	default @Nullable String getIFrameHeight() {
		return null;
	}

	/**
	 * Gets the aspect ratio
	 * 
	 * @return aspect ratio
	 */
	default @Nullable String getIFrameAspectRatio() {
		return null;
	}

	/**
	 * Gets the layout.
	 *
	 * @return the layout
	 */
	default @Nullable String getVimeoLayout() {
		return null;
	}

	/**
	 * Get the iframe source
	 * 
	 * @return iframe source
	 * @throws URISyntaxException
	 */
	default @Nullable String getIFrameSrc() throws URISyntaxException {
		return null;
	}

	/**
	 * Check if empty
	 * 
	 * @return boolean value
	 */
	default boolean isEmpty() {
		return false;
	}
}
