//
//  Home.swift
//  iosApp
//
//  Created by Vikash Parajuli on 10/9/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct Home: View {
    
    @ObservedObject var viewmodel = ViewModels().getRestaurantListViewModel().asObserveableObject()
    var body: some View {
        
        ZStack(){
            switch true {
            case viewmodel.state.error.isError:
                Text(viewmodel.state.error.errorMessage)
                
            case viewmodel.state.isSuccess:
                List{
                    ForEach(viewmodel.state.allRestaurantData!.restaurants , id: \.restaurant){ item in
                        RestaurantRow( restaurant: item.restaurant,onclick: { })
                    }
                }.frame( maxWidth: .infinity)
                    .edgesIgnoringSafeArea(.all)
                    .listStyle(PlainListStyle())
                
            case viewmodel.state.isLoading:
                ProgressView()
            default:
                Text("default")
            }
        }.onAppear(perform: {
            viewmodel.viewModel.onIntent(intent: RestaurantListSideEffects.GetRestaurants())
        })
    }
}

struct Screen_Previews: PreviewProvider {
    static var previews: some View {
        Home()
    }
}
