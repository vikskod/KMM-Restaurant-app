//
//  RestaurantListViewModelObservableObject.swift
//  iosApp
//
//  Created by Vikash Parajuli on 10/9/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import Combine
import shared

public class RestaurantListViewModelObservableObject : ObservableObject {
    
    var viewModel : RestaurantListViewModel


   /**
    *
    * state flow acts as a state for swift ui here
    *
    */
    @Published private(set) var state: RestaurantListState
    
    /**
     **
     *fusing the .asObserveable extension funstion we get the wrapped viewmodel and the stateflow
     */
    init(wrapped: RestaurantListViewModel) {

        viewModel = wrapped
        state = wrapped.state.value as! RestaurantListState
        (wrapped.state.asPublisher() as AnyPublisher<RestaurantListState, Never>)
            .receive(on: RunLoop.main)
            .assign(to: &$state)
    }
    
}
