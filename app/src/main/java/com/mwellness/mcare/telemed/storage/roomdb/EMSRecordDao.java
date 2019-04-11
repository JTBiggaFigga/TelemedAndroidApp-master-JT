package com.mwellness.mcare.telemed.storage.roomdb;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

/**
 * Created by dev01 on 12/30/17.
 */

@Dao
public interface EMSRecordDao {

    @Insert
    public void insertVital(EMSRecord EMSRecord);


    @Query("SELECT * FROM " + EMSRecord.TABLE_NAME + " where " + EMSRecord.COL_USER_ID + "=:userId order by " + EMSRecord.COL_TIMESTAMP_MS + " desc")
    public EMSRecord[] getEMSDesc(final String userId);

    @Query("SELECT * FROM " + EMSRecord.TABLE_NAME + " where " + EMSRecord.COL_USER_ID + "=:userId and " + EMSRecord.COL_SYNCED_AT_MS + "=-1 order by " + EMSRecord.COL_TIMESTAMP_MS)
    public EMSRecord[] getEMSNotSyncedAsc(final String userId);

    @Query("SELECT * FROM " + EMSRecord.TABLE_NAME + " where " + EMSRecord.COL_USER_ID + "=:userId and " + EMSRecord.COL_TIMESTAMP_MS + "=:timestampMs")
    public EMSRecord getVitalRecordByTimestampMs(final String userId, final long timestampMs);

    @Query("SELECT * FROM " + EMSRecord.TABLE_NAME + " where " + EMSRecord.COL_RECORD_ID + "=:recordId")
    public EMSRecord getVitalRecordByRecordId(final String recordId);


    @Query("UPDATE " + EMSRecord.TABLE_NAME + " set " + EMSRecord.COL_SYNCED_AT_MS + "=:syncedAtTs where " + EMSRecord.COL_RECORD_ID + "=:recordId")
    public int setVitalRecordSynced(final String recordId, final long syncedAtTs);



    @Query("DELETE FROM " + EMSRecord.TABLE_NAME + " where " + EMSRecord.COL_SYNCED_AT_MS + " !=-1")
    public int deleteAllSynchronizedEMS();
}
