import React, {ChangeEvent, useEffect, useState} from 'react';
import PropTypes from 'prop-types'; 
import axios from 'axios';
import registerImage from '../../../assets/imgs/loginImage.jpg';
import svglogin from '../../../assets/imgs/login-svgrepo-com.svg';
import {Parking} from "../../../intrfcs/parking/parking";
import {Floor} from "../../../intrfcs/floor/floor";
import {ParkingSpace} from "../../../intrfcs/parkingSpace/parkingspace";
import {useAppDispatch} from "../../../redux/store/store";
import {getParkings} from "../../../redux/actions/parkingActions";
import {RootState} from "../../../redux/reducers/RootState";
import {useSelector} from "react-redux";
import {Booking} from "../../../intrfcs/booking/booking";

interface RegisterFormProps {
}

const BookingForm: React.FC<RegisterFormProps> = () => {


    const { parkings, loading, error } = useSelector((state: RootState) => state.parking);
    const [selectedParking, setSelectedParking] = useState<Parking>();


    const [floors, setFloors] = useState<Floor[]>();
    const [selectedFloor, setSelectedFloor] = useState<Floor>();


    const [parkingSpaces, setParkingSpaces] = useState<ParkingSpace[]>();
    const [selectedParkingSpace, setSelectedParkingSpace] = useState<ParkingSpace>();


    const [rFrom, setRFrom] = useState<Date>();
    const [rTo, setRTo] = useState<Date>();

    const [errorr, setErrorr] = useState('');

    const dispatch = useAppDispatch();



    useEffect(() => {
        dispatch(getParkings());
    }, [dispatch]);


    const bookingData:Booking = {
        rFrom:'',
        rTo:'',
        parkingSpace:''
    }

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/api/v1/reservations', bookingData);
        } catch (error) {
            setErrorr('Booking failed');
        }

        console.log(bookingData)
    };

    const handleParkingChange = async (event: React.ChangeEvent<HTMLSelectElement>) => {

        // Update selectedParking state using a functional update for potential race conditions
        setSelectedParking((prevParking: Parking | undefined ) =>
            parkings.find((parking) => parking.id === event.target.value)
        );

        // Await the completion of the state update for guaranteed consistency
        const selectedParking: Parking | null = await new Promise<Parking | null>((resolve) => {
            setSelectedParking((prevParking: Parking | undefined) => {
                const newParking = parkings.find((parking) => parking.id === event.target.value);
                // @ts-ignore
                resolve(newParking);
                return newParking;
            });
        });

        // Access the updated selectedParking value after the state update is complete
        if (selectedParking) {
            setFloors(selectedParking.floors);
        } else {
            // Handle the case where selectedParking is not found (optional)
            console.warn('Selected parking not found');
        }
    };


    const handleFloorChange = async (event: React.ChangeEvent<HTMLSelectElement>) => {

        // Update selectedParking state using a functional update for potential race conditions
        setSelectedFloor((prevParking: Floor | undefined ) =>
            floors?.find((floor) => floor.id === event.target.value)
        );

        // Await the completion of the state update for guaranteed consistency
        const selectedFloor: Floor | null = await new Promise<Floor | null>((resolve) => {
            setSelectedFloor((prevFloor: Floor | undefined) => {
                const newFloor = floors?.find((floor) => floor.id === event.target.value);
                // @ts-ignore
                resolve(newFloor);
                return newFloor;
            });
        });

        // Access the updated selectedParking value after the state update is complete
        if (selectedFloor) {
            setParkingSpaces(selectedFloor.parkingSpaces);
        } else {
            // Handle the case where selectedParking is not found (optional)
            console.warn('Selected floor not found');
        }
    };


    const handleParkingSpaceChange = (e: ChangeEvent<HTMLSelectElement>) => {


        console.log(e.target.value)

        bookingData.parkingSpace = e.target.value;

        /*
        setSelectedParkingSpace((prevParkingSpace: ParkingSpace | undefined ) =>
            parkingSpaces?.find((parkingspace) => parkingspace.id === e.target.value)
        );

        // Await the completion of the state update for guaranteed consistency
        const selectedParkingSpace: ParkingSpace | null = await new Promise<ParkingSpace | null>((resolve) => {
            setSelectedParkingSpace((prevParkingSpace: ParkingSpace | undefined) => {
                const newParkingSpace = parkingSpaces?.find((parkingspace) => parkingspace.id === e.target.value);
                // @ts-ignore
                resolve(newParkingSpace);
                return newParkingSpace;
            });
        });

        // Access the updated selectedParking value after the state update is complete
        if (selectedParkingSpace) {
            bookingData.parkinSpace = selectedParkingSpace.id;
        } else {
            // Handle the case where selectedParking is not found (optional)
            console.warn('Selected parking space not found');
        }

         */

    };

    const handleRFromChange = (e: ChangeEvent<HTMLInputElement>) => {
        bookingData.rFrom = e.target.value;
    }


    const handleRToChange = (e: ChangeEvent<HTMLInputElement>) => {
        bookingData.rTo = e.target.value;
    }

    return (
        <div className="flex justify-center h-screen">
            <div className="hidden bg-cover lg:block lg:w-2/3">
                <div className="flex-1">
                    <form onSubmit={handleSubmit}>
                        {errorr && <div className="mb-4 text-red-500">{errorr}</div>}
                        <div className="p-field mb-4">
                            <label htmlFor="parking" className="block text-gray-700">Parking</label>
                            <select
                                id="parking"
                                name="parking"
                                required={true}
                                onChange={handleParkingChange}
                                className="w-full mt-1 p-2 border rounded-md focus:outline-none focus:ring-blue-500 focus:border-blue-500"
                            >
                                <option value="">Select a parking</option>
                                {parkings.map(parking => (
                                    <option key={parking.id} value={parking.id}>{parking.name}</option>
                                ))}
                            </select>
                        </div>

                        <div className="p-field mb-4">
                            <label htmlFor="floor" className="block text-gray-700">Floor</label>
                            <select
                                id="floor"
                                name="floor"
                                required={true}
                                onChange={handleFloorChange}
                                className="w-full mt-1 p-2 border rounded-md focus:outline-none focus:ring-blue-500 focus:border-blue-500"
                            >
                                <option value="">Select a floor</option>
                                {floors?.map(floor => (
                                    <option key={floor.id} value={floor.id}>{floor.nbr}</option>
                                ))}
                            </select>
                        </div>

                        <div className="p-field mb-4">
                            <label htmlFor="parkingspace" className="block text-gray-700">Parking Space</label>
                            <select
                                id="parkingspace"
                                name="parkingspace"
                                required={true}
                                onChange={handleParkingSpaceChange}
                                className="w-full mt-1 p-2 border rounded-md focus:outline-none focus:ring-blue-500 focus:border-blue-500"
                            >
                                <option value="">Select a parking space</option>
                                {parkingSpaces?.map(parkingspace => (
                                    <option key={parkingspace.id} value={parkingspace.id}>{parkingspace.nbr}</option>
                                ))}
                            </select>
                        </div>

                        <div className="p-field">
                            <label htmlFor="rfrom" className='block text-gray-700'>Book From</label>
                            <input id="rfrom" name="rfrom" type="datetime-local" onChange={handleRFromChange}
                                   className="w-full mt-1 p-2 border rounded-md focus:outline-none focus:ring-blue-500 focus:border-blue-500"/>
                        </div>

                        <div className="p-field">
                            <label htmlFor="rto" className='block text-gray-700'>Book To</label>
                            <input id="rto" name="rto" type="datetime-local" onChange={handleRToChange}
                                   className="w-full mt-1 p-2 border rounded-md focus:outline-none focus:ring-blue-500 focus:border-blue-500"/>
                        </div>

                        <div className="mt-6">
                            <button type="submit"
                                    className="w-full px-4 py-2 tracking-wide text-white transition-colors duration-300 transform bg-blue-700 rounded-lg hover:bg-blue-500 focus:outline-none focus:bg-green-400 focus:ring focus:ring-green-300 focus:ring-opacity-50">
                                Add Admin
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
};

BookingForm.propTypes = {
    onRegister: PropTypes.func.isRequired
};

export default BookingForm;
