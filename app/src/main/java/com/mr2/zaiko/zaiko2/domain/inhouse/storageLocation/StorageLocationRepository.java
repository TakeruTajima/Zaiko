package com.mr2.zaiko.zaiko2.domain.inhouse.storageLocation;

public interface StorageLocationRepository {
    StorageLocation get(StorageLocationId storageLocationId);

    boolean save(StorageLocation storageLocation);
}
