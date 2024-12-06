package com.shrss.core.services;

import java.util.List;
import java.util.Map;

// TODO: Auto-generated Javadoc
/**
 * The Interface TagsPathMappingConfigService.
 */
public interface TagsPathMappingConfigService {

	/**
	 * Gets the tags path mapping.
	 *
	 * @return the tags path mapping
	 */
	public Map<String, String> getTagsPathMapping();


	/**
	 * Gets the all tags by path.
	 *
	 * @param path the path
	 * @return the all tags by path
	 */
	public List<String> getAllTagsByPath(String path);

}
