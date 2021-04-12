package base;


import data.SpaceMarine;
import file_works.FileKeeper;
import file_works.Transformer;
import messenger.Messenger;

import java.util.*;

/**
 * \
 * Class for working with our collection.
 */
public class CollectionManager implements CollectionKeeper {
    private NavigableMap<Integer, SpaceMarine> marinesCollection;
    private FileKeeper fileManager;
    private Date lastSave;
    private Date lastInitialization;
    private Messenger messenger;
    private Transformer transform;


    public CollectionManager(FileKeeper fileManager, Messenger messenger, Transformer transform) {
        this.fileManager = fileManager;
        this.messenger = messenger;
        this.transform = transform;
        marinesCollection = new TreeMap<>();
    }

    @Override
    public void convertToCollection() {
        marinesCollection = transform.convertFromJson();
        if (marinesCollection == null) marinesCollection = new TreeMap<>();
        lastInitialization = new Date();
    }


    /**
     * Clearing a collection.
     */
    @Override
    public void clearCollection() {
        marinesCollection.clear();
    }

    /**
     * @return size of collection
     */
    @Override
    public int size() {
        return marinesCollection.size();
    }

    /**
     * Get collection from File Manager
     */


    /**
     * @return Name of the collection's type.
     */
    @Override
    public String getType() {
        return marinesCollection.getClass().getName();
    }

    @Override
    public Date getLastInitialization() {
        return lastInitialization;
    }

    @Override
    public Date getLastSave() {
        return lastSave;
    }


    /**
     * @return Collection's path.
     */
    @Override
    public String getPath() {
        if (fileManager.getPath() == null) return "-";
        else return fileManager.getPath();
    }

    @Override
    public void saveCollection() {
        fileManager.writeCollection(marinesCollection);
        lastSave = new Date();
    }

    /**
     * Add element to collection.
     *
     * @param key         Needed to save the key when updating.
     * @param spaceMarine The soldier to be added to the collection.
     */
    @Override
    public void insertToCollection(Integer key, SpaceMarine spaceMarine) {
        if (key == null) {
            marinesCollection.put((int) (Math.random() * 10000), spaceMarine);
        } else marinesCollection.put(key, spaceMarine);
    }

    /**
     * @return unique ID
     */
    @Override
    public Integer nextId() {
        int k = 0;
        int p = (int) (Math.random() * 10000);
        for (Integer i : marinesCollection.keySet()) {
            if (p != marinesCollection.get(i).getId()) {
                k++;
            }
        }
        if (k == 0) {
            return p;
        } else return (p + (int) (Math.random() * 10000));
    }

    /**
     * removes an element by key.
     *
     * @param key
     */
    @Override
    public void removeByKey(Integer key) {
        sortCollection();
        marinesCollection.remove(key);
    }

    /**
     * remove an element by id.
     *
     * @param id Id of element.
     */
    @Override
    public void removeById(Integer id) {
        Map.Entry<Integer, SpaceMarine> entry;
        Integer finalKey = null;
        for (Integer i : marinesCollection.keySet()) {
            if (marinesCollection.get(i).getId().equals(id)) finalKey = i;
        }
        for (Iterator<Map.Entry<Integer, SpaceMarine>> it = marinesCollection.entrySet().iterator(); it.hasNext(); ) {
            entry = it.next();
            if (entry.getKey().equals(finalKey)) {
                it.remove();
            }
        }
    }

    /**
     * removes from the collection all elements whose key is less than the given one.
     *
     * @param key
     * @return number of items removed.
     */
    @Override
    public int removeByKeyIfLower(Integer key) {
        Map.Entry<Integer, SpaceMarine> entry;
        int k = 0;
        for (Iterator<Map.Entry<Integer, SpaceMarine>> it = marinesCollection.entrySet().iterator(); it.hasNext(); ) {
            entry = it.next();
            if (entry.getKey() < key) {
                k = k + 1;
                it.remove();
            }
        }
        return k;
    }

    /**
     * @param key of Soldier
     * @return returns the soldier by key
     */
    @Override
    public SpaceMarine getByKey(Integer key) {
        if (marinesCollection.get(key) == null)
            return null;
        else return marinesCollection.get(key);
    }

    /**
     * @param id of Soldier
     * @return returns key by id
     */
    @Override
    public Integer getKeyById(Integer id) {
        sortCollection();
        for (Integer i : marinesCollection.keySet()) {
            if (marinesCollection.get(i).getId().equals(id)) return i;
        }
        return null;
    }


    /**
     * @return the average value of the height field for all elements in the collection
     */
    @Override
    public float getAverageOfHeight() {
        float averageOfHeight = 0;
        for (Integer i : marinesCollection.keySet()) {
            averageOfHeight += marinesCollection.get(i).getHeight();
        }
        averageOfHeight = averageOfHeight / marinesCollection.size();
        return averageOfHeight;
    }

    /**
     * @return string of our heartCounts and id's
     */
    @Override
    public String getSortedHeartCounts() {
        String str = "";
        for (Integer i : marinesCollection.keySet()) {
            str = str + "id: " + marinesCollection.get(i).getId() + ", heartCount: " + marinesCollection.get(i).getHeartCount() + "\n";
        }
        return str;
    }


    /**
     * @return array of collection category count
     */
    @Override
    public int[] getCountingCategory() {
        int f1 = 0;
        int f2 = 0;
        int f3 = 0;
        int f4 = 0;
        int f5 = 0;
        int[] array = new int[5];
        for (Integer i : marinesCollection.keySet()) {
            if (marinesCollection.get(i).getCategory().name().equals("SCOUT")) {
                f1++;
            } else if (marinesCollection.get(i).getCategory().name().equals("DREADNOUGHT")) {
                f2++;
            } else if (marinesCollection.get(i).getCategory().name().equals("AGGRESSOR")) {
                f3++;
            } else if (marinesCollection.get(i).getCategory().name().equals("SUPPRESSOR")) {
                f4++;
            } else if (marinesCollection.get(i).getCategory().name().equals("CHAPLAIN")) {
                f5++;
            }
            for (int j = 0; j < 5; j++) {
                array[0] = f1;
                array[1] = f2;
                array[2] = f3;
                array[3] = f4;
                array[4] = f5;
            }
        }
        return (array);
    }

    /**
     * sorts the collection by heartCount
     */
    @Override
    public void sortCollection() {
        TreeMap<Integer, SpaceMarine> treeMap = new TreeMap<>();
        List<SpaceMarine> list = new ArrayList<SpaceMarine>(marinesCollection.values());

        list.sort(new Comparator<SpaceMarine>() {
            @Override
            public int compare(SpaceMarine o1, SpaceMarine o2) {
                if (o1.getHeartCount() == o2.getHeartCount()) return 0;
                else if (o1.getHeartCount() > o2.getHeartCount()) return -1;
                else return 1;
            }
        });

        for (int i = 0; i < list.size(); i++) {
            treeMap.put(i, list.get(i));
        }
        marinesCollection = treeMap;
    }


    /**
     * removes all elements from the collection that are greater than the specified one
     *
     * @param marineToCompare soldier to be compared
     * @return number of items removed
     */
    @Override
    public int removeGreater(SpaceMarine marineToCompare) {
        Map.Entry<Integer, SpaceMarine> entry;
        int k = 0;
        for (Iterator<Map.Entry<Integer, SpaceMarine>> it = marinesCollection.entrySet().iterator(); it.hasNext(); ) {
            entry = it.next();
            if (entry.getValue().getHeartCount() > marineToCompare.getHeartCount()) {
                it.remove();
                k++;
            }
        }
        return k;
    }

    /**
     * @param key    key of marine.
     * @param marine soldier to be compared
     * @return replacement status (true or false)
     */
    @Override
    public boolean replaceIfLowe(Integer key, SpaceMarine marine) {
        if (marine.getHeartCount() < marinesCollection.get(key).getHeartCount()) {
            marinesCollection.replace(key, marine);
            return true;
        }
        return false;
    }

    @Override
    public NavigableMap<Integer, SpaceMarine> getMarinesCollection() {
        return marinesCollection;
    }
}