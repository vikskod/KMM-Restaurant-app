//
//  ObservableExtensions.swift
//  iosApp
//
//  Created by Vikash Parajuli on 10/9/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared

public extension RestaurantListViewModel {
    
    func asObserveableObject() -> RestaurantListViewModelObservableObject{
        return RestaurantListViewModelObservableObject(wrapped: self)
    }
    
}
