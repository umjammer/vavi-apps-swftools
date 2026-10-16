/*
 * Copyright (c) 2026 by Naohide Sano, All rights reserved.
 *
 * Programmed by Naohide Sano
 */

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.CountDownLatch;

import com.mgatelabs.swftools.FlashExploitGui;
import com.mgatelabs.swftools.exploit.gui.GuiDisplay;
import com.mgatelabs.swftools.support.plugins.AdvancedConverter;
import com.mgatelabs.swftools.support.swf.io.FlashFilter;
import com.mgatelabs.swftools.support.swf.io.FlashReader;
import com.mgatelabs.swftools.support.swf.objects.FMovie;
import com.mgatelabs.swftools.support.swf.objects.FMovieData;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * TestCase.
 *
 * @author <a href="mailto:umjammer@gmail.com">Naohide Sano</a> (nsano)
 * @version 0.00 2026-10-16 nsano initial version <br>
 */
class TestCase {

    @Test
    @EnabledIfSystemProperty(named = "vavi.test", matches = "ide")
    void test1() throws Exception {
        FlashExploitGui.main(new String[] {"tmp/family.swf"});

        CountDownLatch cdl = new CountDownLatch(1);
        cdl.await();
    }

    @Test
    @EnabledIfSystemProperty(named = "vavi.test", matches = "ide")
    void test2() throws Exception {
        File swfFile = new File("tmp/family.swf");
        if (!swfFile.exists()) {
            return;
        }

        FlashFilter filter = new FlashFilter();
        filter.setFilter(FlashFilter.SHAPE, true);
        filter.setFilter(FlashFilter.SOUND, false);
        filter.setFilter(FlashFilter.FONT, true);
        filter.setFilter(FlashFilter.BITMAP, true);
        filter.setFilter(FlashFilter.BITMAP_DISCARD, false);

        FlashReader reader = new FlashReader(swfFile, filter);
        assertTrue(reader.ready());
        FMovie movie = reader.parse();
        reader.close();
        assertNotNull(movie, reader.getError());

        AdvancedConverter converter = new AdvancedConverter();
        converter.update(movie);
        assertTrue(movie.ready());

        FMovieData movieData = new FMovieData(movie);
        movieData.nextFrame();
        assertFalse(movieData.getDisplayList().isEmpty());

        int w = (int) (movie.getRect().xmax / 20.0f);
        int h = (int) (movie.getRect().ymax / 20.0f);
        GuiDisplay display = new GuiDisplay();
        display.setSize(w, h);
        display.changeMovieDimensions(w, h);
        display.updateMovieSource(movieData);

        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = img.createGraphics();
        display.paint(g2);
        g2.dispose();
    }
}
