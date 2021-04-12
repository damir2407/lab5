package validate;


import data.AstartesCategory;
import data.Chapter;
import data.Coordinates;
import data.SpaceMarine;
import messenger.Messenger;

import java.util.Date;
import java.util.HashMap;
import java.util.NavigableMap;

/**
 * class for check marine's fields
 */

public class FieldsValidation implements Validator {
    private Messenger messenger;
    private ResultKeeper result;


    public FieldsValidation(Messenger messenger, ResultKeeper result) {
        this.messenger = messenger;
        this.result = result;
    }

    /**
     * @param coordinates to check
     * @return coordinates
     */
    @Override
    public Result finalCheckCoordinates(Coordinates coordinates) {
        if (coordinates == null) return result.error(messenger.incorrectCoordinatesMessage());
        return result.ok(coordinates);
    }

    /**
     * @param x to check
     * @return x coordinate
     */
    @Override
    public Result finalCheckX(String x) {
        Float newX;
        try {
            newX = Float.parseFloat(x);
        } catch (NumberFormatException e) {
            return result.error(messenger.incorrectXCoordinateMessage());
        }
        if (!(newX != null && newX <= 610))
            return result.error(messenger.incorrectXCoordinateMessage());
        return result.ok(newX);
    }

    /**
     * @param y to check
     * @return y coordinate
     */
    @Override
    public Result finalCheckY(String y) {
        Double newY = null;
        try {
            newY = Double.parseDouble(y);
        } catch (NumberFormatException e) {
            return result.error(messenger.incorrectYCoordinateMessage());
        }
        if (newY == null)
            return result.error(messenger.incorrectYCoordinateMessage());
        return result.ok(newY);
    }

    /**
     * @param chapterName to check
     * @return chapter name
     */
    @Override
    public Result finalCheckChapterName(String chapterName) {
        if (chapterName == null || chapterName.isEmpty())
            return result.error(messenger.incorrectChapterNameMessage());
        return result.ok(chapterName);
    }

    /**
     * @param chapterWorld to check
     * @return chapter world
     */
    @Override
    public Result finalCheckChapterWorld(String chapterWorld) {
        if (chapterWorld == null)
            return result.error(messenger.incorrectChapterWorldMessage());
        return result.ok(chapterWorld);
    }

    /**
     * @param chapter to check
     * @return chapter
     */
    @Override
    public Result finalCheckChapter(Chapter chapter) {
        try {
        } catch (NullPointerException e) {
            chapter = null;
        }
        return result.ok(chapter);
    }

    /**
     * @param name to check
     * @return name
     */
    @Override
    public Result finalCheckName(String name) {
        if (name == null || name.isEmpty())
            return result.error(messenger.incorrectNameMessage());
        return result.ok(name);
    }

    /**
     * @param date to check
     * @return date
     */
    @Override
    public Result finalCheckCreationDate(Date date) {
        if (date == null)
            return result.error(messenger.incorrectCreationDateMessage());
        return result.ok(date);
    }

    /**
     * @param health to check
     * @return health
     */
    @Override
    public Result finalCheckHealth(String health) {
        Float newHealth;
        try {
            newHealth = Float.parseFloat(health);
        } catch (NumberFormatException e) {
            return result.error(messenger.incorrectHealthMessage());
        }
        if (newHealth == null || newHealth <= 0)
            return result.error(messenger.incorrectHealthMessage());
        return result.ok(newHealth);
    }

    /**
     * @param id to check
     * @return id
     */
    @Override
    public Result finalCheckId(Integer id) {
        if (id == null || id <= 0)
            return result.error(messenger.incorrectIdMessage());
        return result.ok(id);
    }

    /**
     * @param collection to check
     * @return collection
     */
    @Override
    public Result finalCheckIdUniqueness(NavigableMap<Integer, SpaceMarine> collection) {
        HashMap<Integer, Boolean> finalMap = new HashMap<>();
        for (Integer i : collection.keySet()) {
            finalMap.put(collection.get(i).getId(), false);
        }
        if (finalMap.size() != collection.size()) {
            return result.error(messenger.incorrectIdUniquenessMessage());
        }
        return result.ok(collection);
    }

    /**
     * @param heartCount to check
     * @return heartCount
     */
    @Override
    public Result finalCheckHeartCount(String heartCount) {
        Integer newHeartCount;
        try {
            newHeartCount = Integer.parseInt(heartCount);
        } catch (NumberFormatException e) {
            return result.error(messenger.incorrectHeartCountMessage());
        }
        if (newHeartCount == null || newHeartCount <= 0 || newHeartCount > 3)
            return result.error(messenger.incorrectHeartCountMessage());
        return result.ok(newHeartCount);
    }

    /**
     * @param height to check
     * @return height
     */
    @Override
    public Result finalCheckHeight(String height) {
        float newHeight;
        try {
            newHeight = Float.parseFloat(height);
        } catch (NumberFormatException e) {
            return result.error(messenger.incorrectHeightMessage());
        }
        return result.ok(newHeight);
    }

    /**
     * @param category to check
     * @return category
     */
    @Override
    public Result finalCheckCategory(String category) {
        AstartesCategory newCategory;
        try {
            newCategory = AstartesCategory.valueOf(category.toUpperCase());
            if (newCategory == null)
                return result.error(messenger.incorrectCategoryMessage());
        } catch (IllegalArgumentException e) {
            return result.error(messenger.categoryDoesNotExist());
        }
        return result.ok(newCategory);
    }


}
