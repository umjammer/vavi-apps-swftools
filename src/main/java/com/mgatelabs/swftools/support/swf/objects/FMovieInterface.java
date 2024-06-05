/*
    Copyright M-Gate Labs 2007
    Can be edited with permission only.
*/

package com.mgatelabs.swftools.support.swf.objects;

import java.util.List;

import com.mgatelabs.swftools.support.swf.tags.Tag;

// Every Movie Object is Based off this Interface

public interface FMovieInterface {
    // Get the Origonal Clip, that contains all of the objects
    FMovie getCore();

    // General
    int getID();

    long getFrameCount();

    String toString();

    // Animation Tags
    List<?> getTags();

    void addTag(Tag tag);
}