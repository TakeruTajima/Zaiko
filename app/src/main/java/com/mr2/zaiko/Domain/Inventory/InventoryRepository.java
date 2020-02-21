package com.mr2.zaiko.Domain.Inventory;

import com.mr2.zaiko.Domain.User.User;

public interface InventoryRepository {
    void takeNewGoods(Inventory inventory, User user, String from, int quantity, String why);
}
