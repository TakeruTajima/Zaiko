package com.mr2.zaiko.Domain;

public enum ValidateResult {
    NullIsNotAllowed, //Null不可
    Duplicated, //重複
    NumberOfCharactersOver, //文字数オーバー
    NumberOfCharactersLack, //文字数不足
    NumberOutOfRange, //範囲外の数値

    Validity, //有効
}

