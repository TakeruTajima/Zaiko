package com.mr2.zaiko.xOld.Domain.Product;

import com.mr2.zaiko.xOld.Domain.Maker.Maker;

public interface ProductService {
    boolean isDuplicated(Maker maker, Model model);
}
