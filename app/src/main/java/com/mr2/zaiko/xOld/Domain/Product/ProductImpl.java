package com.mr2.zaiko.xOld.Domain.Product;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mr2.zaiko.xOld.Domain.CreatedAt;
import com.mr2.zaiko.xOld.Domain.DeletedAt;
import com.mr2.zaiko.xOld.Domain.UpdateAt;

import java.util.Objects;
import java.util.Optional;

/**
 * Product
 * 部品のルートエンティティ。
 * 識別子ProductIdentity
 */
public class ProductImpl implements Product {
    @Nullable private final Identity productIdentity;
    @NonNull private final Model model;
    private int maker_id;
    @NonNull private Name productName;
    @Nullable private final CreatedAt createdAt;
    @Nullable private final UpdateAt updateAt;
    @Nullable private final DeletedAt deletedAt;

    ProductImpl(@Nullable Identity productIdentity, @NonNull Model model, int maker_id, @NonNull Name productName, @Nullable CreatedAt createdAt, @Nullable UpdateAt updateAt, @Nullable DeletedAt deletedAt) {
        this.productIdentity = productIdentity;
        this.model = model;
        this.maker_id = maker_id;
        this.productName = productName;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.deletedAt = deletedAt;
    }

    /**
     * 品名を変更します。
     * newNameのバリデーションNGの場合、IllegalArgumentExceptionをthrowします。
     * @param newName 品名
     */
    @Override
    public void changeProductName(String newName) {
        if (!Name.validate(newName)) throw new IllegalArgumentException("品名がバリデーションNGです。");
        this.productName = new Name(newName);
    }

    /**
     * IDをintで返します。
     * ID未登録の場合は-1を返します。
     * 現状ID採番はDBに任せる方向なので、未InsertのレコードはIDを持ちませんが、どうしようコレ。
     * Identity.getDefault()で-1を持つデフォルトIDを持つIdentityを持たせることもできます。
     * @return Product identity.
     */
    @Override
    public int getProductIdentity() {
        if (null == productIdentity) return -1;
        return productIdentity.getIdentity();
    }

    @Override
    public String getModel() {
        return model.getModel();
    }

    @Override
    public String getProductName() {
        return productName.getName();
    }

    @Override
    public int getMakerCode() {
        return maker_id;
    }

    public Optional<CreatedAt> getCreatedAtOptional(){
        return Optional.ofNullable(createdAt);
    }

    @Override
    public String getCreatedAt() {
        if (null == createdAt) return "";
        return createdAt.toString();
    }

    @Override
    public String getUpdateAt() {
        if (null == updateAt) return "";
        return updateAt.toString();
    }

    @Override
    public String getDeletedAt() {
        if (null == deletedAt) return "";
        return deletedAt.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductImpl product = (ProductImpl) o;
        return Objects.equals(productIdentity, product.productIdentity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productIdentity);
    }

    public static class Builder{
        private Identity productIdentity;
        private Model model;
        private int maker_id;
        private Name name;
        private CreatedAt createdAt;
        private UpdateAt updateAt;
        private DeletedAt deletedAt;

        public Builder(Model model, int maker_id, Name name) {
            this.model = model;
            this.maker_id = maker_id;
            this.name = name;
        }

        public Product create(){
            if (null == model ||
                    0 >= maker_id ||
                    null == name) throw new IllegalStateException("必須項目が足りません");

            return new ProductImpl(productIdentity,
                    model,
                    maker_id,
                    name,
                    createdAt,
                    updateAt,
                    deletedAt);
        }

        public Builder setProductIdentity(Identity productIdentity) {
            this.productIdentity = productIdentity;
            return this;
        }

        public Builder setCreatedAt(CreatedAt createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setUpdateAt(UpdateAt updateAt) {
            this.updateAt = updateAt;
            return this;
        }

        public Builder setDeletedAt(DeletedAt deletedAt) {
            this.deletedAt = deletedAt;
            return this;
        }
    }
}
