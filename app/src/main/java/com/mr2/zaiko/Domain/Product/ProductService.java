package com.mr2.zaiko.Domain.Product;

import com.mr2.zaiko.Domain.Maker.Maker;

public interface ProductService {
    boolean isDuplicated(Maker maker, Model model);
}
