package com.mr2.zaiko.Domain.Item;

import android.content.Context;
import android.database.Cursor;

import com.mr2.zaiko.Domain.Base.BaseCrudRepository;
import com.mr2.zaiko.Domain.Company.Company;
import com.mr2.zaiko.Domain.Id;
import com.mr2.zaiko.Domain.UnitType.UnitType;

import java.util.List;

public class ItemRepositoryImpl extends BaseCrudRepository implements ItemRepository {
    public static final String TAG = ItemRepositoryImpl.class.getSimpleName();
    public final List<Company> makerList;
    public final List<UnitType> unitTypeList;

    public ItemRepositoryImpl(Context context, List<Company> makerList, List<UnitType> unitTypeList) {
        super(context);
        this.makerList = makerList;
        this.unitTypeList = unitTypeList;
        //todo:集約から他の集約のEntityを参照するには、Idによる事後参照（非推奨）か、Repositoryに予め集約を渡しておく。らしい。
    }

    @Override
    public List<Item> findAllByUnDeleted() {
        Cursor c = adapter.findAllRecordsByNull("m_items", "deleted_at");
        return ItemConverter.convert(c, makerList, unitTypeList);
    }

    @Override
    public long countByUnDeleted() {
        List<Item> itemList = findAllByUnDeleted();
        return itemList.size();
    }

    @Override
    public List<Item> findAllByMaker(Company maker) {
        Id id = maker.get_id();
//        if (null == id)
        Cursor c = adapter.findAllRecordExactMatch("m_items", "company_id", String.valueOf(id.value()));
        return ItemConverter.convert(c, makerList, unitTypeList); //todo: Id.toString()の内容って普通にintをStringに直すだけのほうがいいのか
    }

    @Override
    public long countByMaker(Company maker) {
        List<Item> list = findAllByMaker(maker);
        return list.size();
    }

    @Override
    public int extractInHouseCode(int maker_id) {
        //メーカーで抽出したItemリストからinHouseCodeの最大値の+1を求める
        List<Item> list = findAllByMaker(Company.of(Id.of(maker_id)));
        int inHouseCodeResult = 1;
        if (null != list && 0 < list.size()){
            for (int i = 0; i < list.size(); i++){
                Item item = list.get(i);
                int inHouseCode = item.getInHouseCode().value();
                if (inHouseCodeResult < inHouseCode){
                    inHouseCodeResult = inHouseCode;
                }
            }
            inHouseCodeResult++;
        }
        return inHouseCodeResult;
    }

    @Override
    public Item findOne(Integer _id) {
        Cursor c = adapter.findOneRecordById("m_items", _id);
        List<Item> list = ItemConverter.convert(c, makerList, unitTypeList);
        if (null == list || 1 != list.size())
            throw new IllegalStateException("idからレコードの特定に失敗しました。");
        return list.get(0);
    }

    @Override
    public boolean exists(Integer _id) {
        Cursor c = adapter.findOneRecordById("m_items", _id);
        List<Item> list = ItemConverter.convert(c, makerList, unitTypeList);
        return (null != list && 1 == list.size());
    }

    @Override
    public List<Item> findAll() {
        Cursor c = adapter.getAllRecords("m_items");
        return ItemConverter.convert(c, makerList, unitTypeList);
    }

    @Override
    public long count() {
        List<Item> list = findAll();
        return list.size();
    }

    @Override
    public Item save(Item entity) {
        adapter.beginTransaction();
        long result = -1;
        Id _id = entity.get_id();
        if (null == _id || !exists(_id.value())){
            result = adapter.insertRecords("m_items", ItemConverter.convert(entity));
            if (result != -1) {
                adapter.commit();
                return findOne((int) result);
            }
        }else {
            result = adapter.updateRecords("m_items", ItemConverter.convert(entity), _id.value());
        }
        if (result != -1) { //todo:commit()してなくてもOKだった
            adapter.commit();
            return findOne(_id.value());
        } else {
            adapter.rollBack();
            throw new IllegalStateException("登録に失敗しました。Item{" + entity.toString() + "}");
        }
        //todo:db.insert()の戻り値は挿入されたレコードのIDで
        // .update()は更新されるレコードの数やぞ　他のRepositoryも水戸家
    }

    @Override
    public void delete(Item entity) {
        if (exists(entity.get_id().value())){
            adapter.updateRecordsByCurrentTimestamp("m_items", "deleted_at", entity.get_id().value());
        }
    }
}
