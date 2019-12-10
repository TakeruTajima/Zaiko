package com.mr2.zaiko.Domain.Product;

import com.mr2.zaiko.Domain.Company.Company;

public interface ProductService {
    boolean isDuplicated(Company maker, Model model);
}
