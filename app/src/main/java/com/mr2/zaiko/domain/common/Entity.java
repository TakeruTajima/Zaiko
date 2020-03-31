package com.mr2.zaiko.domain.common;

public abstract class Entity extends AssertionConcern {

    protected static final int INITIAL_UNMUTATED_VERSION = 0;
    private int unmutatedVersion;

    public Entity(int unmutatedVersion) {
        super();

        this.setUnmutatedVersion(unmutatedVersion);
    }

    public int unmutatedVersion() {
        return this.unmutatedVersion;
    }

    //読み込み時のデータバージョンを保持しておいて
    // 更新かける対象の指定にそのまま渡して
    // 実際の更新の内容に渡すのは+1した数字？（だとEntityとしてverを更新することはない？
    private void setUnmutatedVersion(int unmutatedVersion) {
        this.unmutatedVersion = unmutatedVersion;
    }
}
