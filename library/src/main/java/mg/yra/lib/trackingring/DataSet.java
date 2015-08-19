package mg.yra.lib.trackingring;

import java.util.List;

/**
 * Created by Yvan on 31/07/15.
 */
public class DataSet {

    private List<DataEntry> mEntries;

    public DataSet(List<DataEntry> entries) {
        mEntries = entries;
    }

    public List<DataEntry> getEntries() {
        return mEntries;
    }

    public void setEntries(List<DataEntry> entries) {
        mEntries = entries;
    }

}
