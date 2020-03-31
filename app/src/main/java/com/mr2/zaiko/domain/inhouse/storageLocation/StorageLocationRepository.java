package com.mr2.zaiko.domain.inhouse.storageLocation;

public interface StorageLocationRepository {
    StorageLocation get(StorageLocationId storageLocationId);

    boolean save(StorageLocation storageLocation);
}
