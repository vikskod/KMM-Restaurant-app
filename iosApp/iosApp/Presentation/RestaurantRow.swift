//
//  HeadlineRow.swift
//  iosApp
//
//  Created by Vikash Parajuli on 10/9/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared


struct  RestaurantRow: View {
    
    var restaurant :  RestaurantX
    var onclick:  () -> ()
    
    init( restaurant : RestaurantX, onclick: @escaping () -> ()){
        self.restaurant = restaurant
        self.onclick = onclick
    }
    
    var body: some View {
        ZStack(alignment: .bottomLeading) {
            if #available(iOS 15.0, *) {
                AsyncImage(url: URL(string: restaurant.featuredImage),content: { image in
                    image.resizable()
                        .aspectRatio(contentMode: .fill)
                        .frame(maxWidth: UIScreen.main.bounds.width, maxHeight: 250)
                        .clipped()
                }, placeholder: {Color.gray})
                .frame(height: 250)
            } else {
                // AsyncImage is not available below ios 15
            }
            
            Rectangle()
                .frame( height: 80)
                .opacity(0.25)
                .blur(radius: 10)
            
            VStack(alignment: .leading) {
                Text(restaurant.name)
                    .foregroundColor(.white)
                    .font(.system(size: 20))
                    .fontWeight(.bold)
                
                Text(restaurant.location.address)
                    .foregroundColor(.white)
                    .font(.system(size: 20))
            }.padding(.leading, 8)
        }.padding(.bottom,30)
        
    }
    
}


