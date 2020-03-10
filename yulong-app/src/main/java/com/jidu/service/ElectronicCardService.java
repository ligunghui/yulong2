package com.jidu.service;

import com.jidu.pojo.card.ElectronicCard;

public interface ElectronicCardService {
    void addElectronicCard(ElectronicCard electronicCard);

    void editElectronicCard(ElectronicCard electronicCard);

    ElectronicCard findElectronicCard(String userId);

    void deleteElectronicCard(String userId);
}
