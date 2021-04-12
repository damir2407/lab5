package file_works;

import data.SpaceMarine;
import messenger.Messenger;
import validate.Result;
import validate.ResultKeeper;
import validate.Validator;

import java.util.NavigableMap;

/**
 * To check fields from file
 */
public class FileFieldsChecker {

    private Validator fieldsValidation;
    private Messenger messenger;
    private ResultKeeper result;


    public FileFieldsChecker(Validator fieldsValidation, Messenger messenger, ResultKeeper result) {
        this.fieldsValidation = fieldsValidation;
        this.messenger = messenger;
        this.result = result;
    }

    /**
     * @param collection The collection with which the work is taking place
     * @return status
     */

    public Result check(NavigableMap<Integer, SpaceMarine> collection) {
        for (Integer i : collection.keySet()) {
            if (!fieldsValidation.finalCheckId(collection.get(i).getId()).isOK())
                return result.error(messenger.incorrectIdMessage());
            if (!fieldsValidation.finalCheckIdUniqueness(collection).isOK())
                return result.error(messenger.incorrectIdUniquenessMessage());
            if (!fieldsValidation.finalCheckName(collection.get(i).getName()).isOK())
                return result.error(messenger.incorrectNameMessage());
            if (!fieldsValidation.finalCheckCoordinates(collection.get(i).getCoordinates()).isOK())
                return result.error(messenger.incorrectCoordinatesMessage());
            if (!fieldsValidation.finalCheckX(String.valueOf(collection.get(i).getCoordinates().getX())).isOK())
                return result.error(messenger.incorrectXCoordinateMessage());
            if (!fieldsValidation.finalCheckY(String.valueOf(collection.get(i).getCoordinates().getY())).isOK())
                return result.error(messenger.incorrectYCoordinateMessage());
            if (!fieldsValidation.finalCheckCreationDate(collection.get(i).getCreationDate()).isOK())
                return result.error(messenger.incorrectCreationDateMessage());
            if (!fieldsValidation.finalCheckHealth(String.valueOf(collection.get(i).getHealth())).isOK())
                return result.error(messenger.incorrectHealthMessage());
            if (!fieldsValidation.finalCheckHeartCount(String.valueOf(collection.get(i).getHeartCount())).isOK())
                return result.error(messenger.incorrectHeartCountMessage());
            if (!fieldsValidation.finalCheckCategory(String.valueOf(collection.get(i).getCategory())).isOK())
                return result.error(messenger.incorrectCategoryMessage());
            if (fieldsValidation.finalCheckChapter(collection.get(i).getChapter()).getObject() != null) {
                if (!fieldsValidation.finalCheckChapterName(collection.get(i).getChapter().getName()).isOK())
                    return result.error(messenger.incorrectChapterNameMessage());
                if (!fieldsValidation.finalCheckChapterWorld(collection.get(i).getChapter().getWorld()).isOK())
                    return result.error(messenger.incorrectChapterWorldMessage());
            }
        }
        return result.ok(collection);
    }
}
