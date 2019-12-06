package com.mr2.zaiko.Application;

import android.util.Log;

import com.mr2.zaiko.Domain.UnitType.UnitTypeName;
import com.mr2.zaiko.Domain.UnitType.UnitType;
import com.mr2.zaiko.Domain.UnitType.UnitTypeRepository;

import java.util.Date;
import java.util.List;

public class UnitTypeUseCase {
    private static final String TAG = "UnitTypeUseCase:";
    private UnitTypeRepository repository;
    public enum UnitTypeSelection {
        ALL,
        UN_DELETED
    }

    public boolean deleteUnitType(UnitType entity) {
        if (repository.exists(entity.get_id().value())){
            repository.delete(entity);
            return true;
        }else return false;
    }

    public UnitTypeUseCase(UnitTypeRepository repository) {
        this.repository = repository;
    }

    public void test(){
//        Log.d(TAG, "UseCaseテストを開始します。");
////        Log.d(TAG, "repository.count():"+ repository.count());
//        if (0 == repository.count()){
//            Log.d(TAG, "UnitTypeにレコードがありません。新規作成します。");
//            UnitType entity = repository.save(UnitType.of("testUnitTypeName"));
//            if(null == entity){
//                Log.d(TAG,"作成に失敗しました。");
//            }else {
//                Log.d(TAG, "Build test value success.");
//                Log.d(TAG, "出力します。");
//                Log.d(TAG, "id:" + entity.get_id() + " name:" + entity.getName() + " created_at:" + entity.getCreatedAt() + " deleted_at:" + entity.getDeletedAt());
//            }
//        }else {
//            Log.d(TAG, "UnitTypeにレコードが"+ repository.count() +"件存在しました。");
//            Log.d(TAG, "出力します。");
//
//            List<UnitType> list = repository.findAll();
//            UnitType entity;
//            for (int i = 0; list.size() > i; i++){
//                entity = list.value(i);
//                Log.d(TAG, "id:" + entity.get_id() + " name:" + entity.getName() + " created_at:" + entity.getCreatedAt() + " deleted_at:" + entity.getDeletedAt());
//                if (i >= 100){break;}
//            }
//            Log.d(TAG, "出力が終わりました。");
//        }
//        Log.d(TAG, "UseCaseテストを終了します。");
    }

    public List<UnitType> getList(UnitTypeSelection selection){
        List<UnitType> list;
        switch (selection){
            case ALL:
                list = repository.findAll();
                break;
            case UN_DELETED:
                Log.d(TAG, "now " + new Date().toString());
                list = repository.findAllByUnDeleted();
                break;
            default:
                throw new IllegalStateException("unknown selection error.");
        }
        return list;
    }

    public boolean saveEntity(String name){ //TODO:このあたりからローダーにできないかなーって思ってるけど実装メンドそうなので後回しー
        if (repository.existsByName(name)) return false; //重複チェック
        UnitType entity;
        try {
            entity = UnitType.of(UnitTypeName.of(name));
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            return false;
        }
        return null != repository.save(entity);
    }

    public UnitTypeRepository getRepository(){return repository;}
}
