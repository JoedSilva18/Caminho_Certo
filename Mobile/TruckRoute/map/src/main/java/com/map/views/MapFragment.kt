package com.map.views

import android.Manifest
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.content.ContextCompat
import com.akexorcist.googledirection.GoogleDirection
import com.akexorcist.googledirection.constant.TransportMode
import com.akexorcist.googledirection.model.Direction
import com.akexorcist.googledirection.model.Route
import com.akexorcist.googledirection.util.DirectionConverter
import com.akexorcist.googledirection.util.execute
import com.core.base.BaseFragment
import com.core.dialogs.PostDialog
import com.core.extensions.*
import com.core.model.ImageEnum
import com.core.model.Post
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.map.R
import kotlinx.android.synthetic.main.fragment_map.*
import org.koin.android.architecture.ext.viewModel


class MapFragment : BaseFragment<MapViewModel>(), OnMapReadyCallback,
    GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
    LocationListener {
    override val viewModel by viewModel<MapViewModel>()

    private lateinit var mMap: GoogleMap
    private lateinit var mGoogleApiClient: GoogleApiClient
    private lateinit var mLocationRequest: LocationRequest
    private lateinit var mapFragment: SupportMapFragment
    private lateinit var origin: LatLng
    private var isFirst: Boolean = true
    private var startedRoute: Boolean = false

    private val destination = LatLng(-16.826310, -49.375550)

    private lateinit var markerOne: Marker
    private lateinit var markerTwo: Marker
    private lateinit var markerThree: Marker
    private lateinit var markerFour: Marker
    private lateinit var markerFive: Marker
    private lateinit var markerSix: Marker
    private lateinit var markerSeven: Marker
    private lateinit var markerEight: Marker

    private val latLngOne = LatLng(-22.196987, -47.762799)
    private val latLngTwo = LatLng(-21.871103, -48.147320)
    private val latLngThree = LatLng(-20.940324, -48.443951)
    private val latLngFour = LatLng(-20.014046, -48.674664)
    private val latLngFive = LatLng(-19.186075, -48.938336)
    private val latLngSix = LatLng(-18.249614, -49.256939)
    private val latLngSeven = LatLng(-17.601531, -49.180035)
    private val latLngEight = LatLng(-16.909074, -49.278912)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        setStatusBarTransparent()
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapFragment = SupportMapFragment.newInstance()
        childFragmentManager.beginTransaction().replace(R.id.mapMap, mapFragment).commit()

        initializeComponents()
    }

    override fun onResume() {
        super.onResume()
        if (!permissionIsGranted(Manifest.permission.ACCESS_FINE_LOCATION)) {
            doPermissionRequest(Manifest.permission.ACCESS_FINE_LOCATION)
            return
        }

        if (!permissionIsGranted(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            doPermissionRequest(Manifest.permission.ACCESS_COARSE_LOCATION)
            return
        }

        mapFragment.getMapAsync(this)
    }

    private fun initializeComponents() {
        edtSearchDestination.apply {
            setOnClickListener {
                if (!imgBackMap.present) {
                    imgBackMap.fadeIn()
                }

                if (!imgSearchMap.present) {
                    imgSearchMap.fadeIn()
                }

            }

            editorActionListener(EditorInfo.IME_ACTION_SEARCH) { clickSeach() }
        }

        imgSearchMap.setOnClickListener {
            clickSeach()
        }

        imgBackMap.setOnClickListener { hideContainerSearch() }
        btnStartedMap.setOnClickListener {
            btnStartedMap.fadeOut()
            startedRoute()
            startedRoute = true
        }

        btnConfirmMap.setOnClickListener {
            hideContainerSearch()
            requestDirection()
            btnStartedMap.fadeIn()
        }

    }

    private fun startedRoute() {
        mMap.moveCamera(CameraUpdateFactory.newLatLng(origin))
        mMap.animateCamera(CameraUpdateFactory.zoomTo(19f))
    }

    private fun requestDirection() {
        GoogleDirection.withServerKey(getString(R.string.google_maps_key))
            .from(origin)
            .and(latLngOne)
            .and(latLngTwo)
            .and(latLngThree)
            .and(latLngFour)
            .and(latLngFive)
            .and(latLngSix)
            .and(latLngSeven)
            .and(latLngEight)
            .to(destination)
            .transportMode(TransportMode.DRIVING)
            .execute(
                onDirectionSuccess = { direction -> onDirectionSuccess(direction) },
                onDirectionFailure = { t -> onDirectionFailure(t) }
            )
    }

    private fun onDirectionSuccess(direction: Direction) {
        if (direction.isOK) {

            val route = direction.routeList[0]
            val legCount = route.legList.size

            markerOne = mMap.addMarker(MarkerOptions().position(latLngOne).icon(bitmapDescriptorFromVector(R.drawable.ic_shell_logo)))
            markerTwo = mMap.addMarker(MarkerOptions().position(latLngTwo).icon(bitmapDescriptorFromVector(R.drawable.ic_petrobras_logo)))
            markerThree = mMap.addMarker(MarkerOptions().position(latLngFour).icon(bitmapDescriptorFromVector(R.drawable.ic_petroleo_ipiranga)))
            markerFour = mMap.addMarker(MarkerOptions().position(latLngFive).icon(bitmapDescriptorFromVector(R.drawable.ic_petrobras_logo)))
            markerFive = mMap.addMarker(MarkerOptions().position(latLngSix).icon(bitmapDescriptorFromVector(R.drawable.ic_petroleo_ipiranga)))
            markerSix = mMap.addMarker(MarkerOptions().position(latLngSeven).icon(bitmapDescriptorFromVector(R.drawable.ic_shell_logo)))
            markerSeven = mMap.addMarker(MarkerOptions().position(latLngEight).icon(bitmapDescriptorFromVector(R.drawable.ic_petrobras_logo)))
            markerEight = mMap.addMarker(MarkerOptions().position(destination).icon(bitmapDescriptorFromVector(R.drawable.ic_petrobras_logo)))

            mMap.setOnMarkerClickListener { marker -> onMarkerClick(marker) }

            for (index in 0 until legCount) {
                val leg = route.legList[index]
                if (index == legCount - 1) {
                    mMap.addMarker(MarkerOptions().position(leg.endLocation.coordination).icon(bitmapDescriptorFromVector(R.drawable.ic_map_pin)))
                }
                val stepList = leg.stepList
                val polylineOptionList = context?.let {
                    DirectionConverter.createTransitPolyline(
                        it,
                        stepList,
                        5,
                        Color.RED,
                        3,
                        Color.BLUE
                    )
                }

                polylineOptionList?.let {
                    for (polylineOption in it) {
                        mMap.addPolyline(polylineOption)
                    }
                }
            }
            setCameraWithCoordinationBounds(route)
        }
    }

    private fun onDirectionFailure(t: Throwable) {
    }

    private fun setCameraWithCoordinationBounds(route: Route) {
        val southwest = route.bound.southwestCoordination.coordination
        val northeast = route.bound.northeastCoordination.coordination
        val bounds = LatLngBounds(southwest, northeast)

        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 200))
    }


    private fun clickSeach() {
        edtSearchDestination.hideSoftInput()

        if (!cardBackgroundMap.present) {
            cardBackgroundMap.fadeIn()
        }

        if (!containerDataRouteMap.present) {
            containerDataRouteMap.fadeIn()
        }
    }

    private fun hideContainerSearch() {
        edtSearchDestination.hideSoftInput()

        if (imgBackMap.present) {
            imgBackMap.fadeOut()
        }

        if (imgSearchMap.present) {
            imgSearchMap.fadeOut()
        }

        if (cardBackgroundMap.present) {
            cardBackgroundMap.fadeOut()
        }

        if (containerDataRouteMap.present) {
            containerDataRouteMap.fadeOut()
        }
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        buildGoogleApiClient()
        mMap.uiSettings.isZoomControlsEnabled = false
        mMap.isMyLocationEnabled = true
        mMap.uiSettings.isMapToolbarEnabled = false
        mMap.uiSettings.isMyLocationButtonEnabled = false
    }

    override fun onLocationChanged(location: Location?) {
        if (location != null) {
            origin = LatLng(location.latitude, location.longitude)

            if (isFirst) {
                mMap.moveCamera(CameraUpdateFactory.newLatLng(origin))
                mMap.animateCamera(CameraUpdateFactory.zoomTo(18f))
                isFirst = false
            }

            if (startedRoute) {
                mMap.moveCamera(CameraUpdateFactory.newLatLng(origin))
                mMap.animateCamera(CameraUpdateFactory.zoomTo(19f))
            }
        }
    }

    @Synchronized
    private fun buildGoogleApiClient() {
        safeLet(context) { context ->
            mGoogleApiClient = GoogleApiClient.Builder(context)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build()
            mGoogleApiClient.connect()
        }
    }

    override fun onConnected(bundle: Bundle?) {
        mLocationRequest = LocationRequest()
        mLocationRequest.interval = 3000
        mLocationRequest.fastestInterval = 3000
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        LocationServices.FusedLocationApi.requestLocationUpdates(
            mGoogleApiClient,
            mLocationRequest,
            this
        )
    }

    override fun onConnectionSuspended(p0: Int) {
        TODO("Not yet implemented")
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("Not yet implemented")
    }

    private fun onMarkerClick(marker: Marker?): Boolean {
        return safeLet(context) { context ->
            when (marker) {
                markerOne -> {
                    PostDialog(
                        context,
                        Post(
                            image = ImageEnum.SHELL,
                            title = "Posto Shell",
                            distance = "Distancia 200km",
                            food = "Comida: Sim",
                            shower = "Banho: Sim",
                            stars = 5f
                        )
                    ).show()
                }
                markerTwo -> {
                    PostDialog(
                        context,
                        Post(
                            image = ImageEnum.BR,
                            title = "Posto Petrobras",
                            distance = "Distancia 350km",
                            food = "Comida: Sim",
                            shower = "Banho: Não",
                            stars = 3f
                        )
                    ).show()
                }
                markerThree -> {
                    PostDialog(
                        context,
                        Post(
                            image = ImageEnum.IPIRANGA,
                            title = "Posto Ipiranga",
                            distance = "Distancia 400km",
                            food = "Comida: Não",
                            shower = "Banho: Não",
                            stars = 2f
                        )
                    ).show()
                }
                markerFour -> {
                    PostDialog(
                        context,
                        Post(
                            image = ImageEnum.BR,
                            title = "Posto Petrobras",
                            distance = "Distancia 500km",
                            food = "Comida: Sim",
                            shower = "Banho: Sim",
                            stars = 5f
                        )
                    ).show()
                }
                markerFive -> {
                    PostDialog(
                        context,
                        Post(
                            image = ImageEnum.IPIRANGA,
                            title = "Posto Ipiranga",
                            distance = "Distancia 580km",
                            food = "Comida: Sim",
                            shower = "Banho: Sim",
                            stars = 4f
                        )
                    ).show()
                }
                markerSix -> {
                    PostDialog(
                        context,
                        Post(
                            image = ImageEnum.SHELL,
                            title = "Posto Shell",
                            distance = "Distancia 680km",
                            food = "Comida: Não",
                            shower = "Banho: Sim",
                            stars = 3f
                        )
                    ).show()
                }
                markerSeven -> {
                    PostDialog(
                        context,
                        Post(
                            image = ImageEnum.BR,
                            title = "Posto Petrobras",
                            distance = "Distancia 790km",
                            food = "Comida: Não",
                            shower = "Banho: Não",
                            stars = 2f
                        )
                    ).show()
                }
                markerEight -> {
                    PostDialog(
                        context,
                        Post(
                            image = ImageEnum.BR,
                            title = "Posto Petrobras",
                            distance = "Distancia 900km",
                            food = "Comida: Sim",
                            shower = "Banho: Sim",
                            stars = 5f
                        )
                    ).show()
                }
                else -> {
                    false
                }
            }
            true
        } ?: false
    }

    private fun bitmapDescriptorFromVector(
        vectorResId: Int
    ): BitmapDescriptor? {
        val vectorDrawable: Drawable = context?.let { ContextCompat.getDrawable(it, vectorResId) }
            ?: resources.getDrawable(R.drawable.ic_map_pin)

        vectorDrawable.setBounds(
            0,
            0,
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight
        )
        val bitmap: Bitmap = Bitmap.createBitmap(
            vectorDrawable.intrinsicWidth,
            vectorDrawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        vectorDrawable.draw(canvas)
        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }
}