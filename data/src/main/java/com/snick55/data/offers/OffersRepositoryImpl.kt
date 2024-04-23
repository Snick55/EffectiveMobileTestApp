package com.snick55.data.offers

import com.snick55.domain.entities.AviaOffer
import com.snick55.domain.repositories.OffersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OffersRepositoryImpl @Inject constructor(
private val dataSource: OffersDataSource
): OffersRepository {

    override fun getAllOffers(): Flow<List<AviaOffer>> = flow {
        emit(dataSource.getOffers().offers.map {
            it.toAviaOffer()
        })
    }



}

fun OffersResponse.Offer.toAviaOffer() = AviaOffer(id,title,town,"${price.value.toString()[0]} ${price.value.toString().substring(1)}",when(id){
    1 -> "https://s3-alpha-sig.figma.com/img/50e5/7703/05c9ccd1fe0c75a7447cfd59dcce0842?Expires=1714348800&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=Op6qKRY0UWEJAkRwwrmm5nEo93WEb5Fbv1SJHextJcXKQNMKTjHBSEawukcj294BrAWvfnOCQqn2FabfMIQblfep1NkMNRtro3YWpbHKbVR0l29Ba8oiZ00zrg0f~~VNTmG9k4qJes7XDzXpuvH9w6dBtkrog1UOW1DCtaGcIKqQ~sTRbBg1oDCe7GlSMy0-~~qgGuRrrN5if1kiFEkuNt6r47OT2dQbBu2EAIvDGYKKnnqCuqjHu9l-ymoHr8quXvsH7x0s0ETXxi4Krr2opKCtbGFIHnGng84KHuLe6d-uJD1lYV1Zfl031bsLbwSEtsJfZoMcQMgutrV~Rv0EWw__"
    2->"https://s3-alpha-sig.figma.com/img/7ee0/2366/49ac0bef4b3a0bbe25b20342dccbb8f3?Expires=1714348800&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=Ssh0NsrSVmZk2TAprNUiTC-aSRVOGMPWqKRSJ6TyxqB5Tmel3KGP0EkF8tUzZxv1cnbPXmavHww-jYoEOAJz2E07Vke65QT5k83raBYbpprJhNphqwOzkE-wX5bLr33oG5m5z8PqOb4yti0q9Mp~7SXycUBn5kJbu8LICGLepunWmXC-VRDEpJmFhZ5scgzygsBNYrjG8lx1utcBfaTFHUqTo4FkHS0oaXl8UfmDjxYPep2JqLhlKLqHw9QF9k7KNaJaV~6PAmOF6hW0NP2d5yc1ZNpBX54YoPfQIn94UjFurFWug-bxYYpYaaEYeuFFR9X0BflGBAC0YMR2d1quQg__"
    else -> "https://s3-alpha-sig.figma.com/img/52b5/fb72/4e159245d20766d9474526f6e4e7d452?Expires=1714348800&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=nXLiXUst08KCcf8xN-jX7Yp622BdE8Kd30Fi30OWF4TNDOLeuGaAM~oouy~nXBjos8Lm~fMXHWM-uxPcEql3ZKJewp~2flaJIJqu0igqnEPEzvGo56hLWTlg5i9F3tj88kHHwqtAVFN5huXxTe46pVv~Q6WCxZUZDBIITK0mVTl5Q0UreF3v5OlwBxelumB8FiX4iIDDdFy5FJw8Ez-cJGntE-rSjwnOTu2y2tCJru3-SJf9-6A2zlM1MSwX4NNgTXBxSVcsNEs5YzuPNrTUbuAS~JSWn2pyYmfXFOlWBa8XH-xdagsxuV2bfDD6k0sM8D5Zojz~o32~oDd8FU2uRg__"
})