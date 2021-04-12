package validate;

import data.Chapter;
import data.Coordinates;
import data.SpaceMarine;

import java.util.Date;
import java.util.NavigableMap;

/**
 * interface for FieldsValidation to validate fields
 */

public interface Validator {

    Result finalCheckName(String name);

    Result finalCheckCreationDate(Date date);

    Result finalCheckHealth(String health);

    Result finalCheckId(Integer id);

    Result finalCheckIdUniqueness(NavigableMap<Integer, SpaceMarine> collection);

    Result finalCheckHeartCount(String heartCount);

    Result finalCheckHeight(String height);

    Result finalCheckCategory(String category);

    Result finalCheckCoordinates(Coordinates coordinates);

    Result finalCheckX(String x);

    Result finalCheckY(String y);

    Result finalCheckChapterName(String chapterName);

    Result finalCheckChapterWorld(String chapterWorld);

    Result finalCheckChapter(Chapter chapter);


}
