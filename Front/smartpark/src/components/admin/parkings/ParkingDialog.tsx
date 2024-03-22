import React, { ChangeEvent, useEffect, useState } from 'react';
import { Dialog } from 'primereact/dialog';
import { Button } from 'primereact/button';
import { createParking,updateParking,getParkings} from "../../../redux/actions/parkingActions";
import {useAppDispatch} from "../../../redux/store/store";
import {Parking} from "../../../intrfcs/parking/parking";

interface ParkingDialogProps {
    visible: boolean;
    onHide: () => void;
    refreshData: () => void;
    selectedParking?: Parking | null;
}

const ParkingDialog: React.FC<ParkingDialogProps> = ({ visible, onHide, refreshData, selectedParking }) => {
    const dispatch = useAppDispatch();
    const [parkingData, setParkingData] = useState<Partial<Parking>>({ name: '' });


    useEffect(() => {
        if (selectedParking) {
            setParkingData(selectedParking);
        } else {
            setParkingData({ name: '' });
        }
    }, [selectedParking]);

    const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setParkingData(prevData => ({ ...prevData, [name]: value }));
    };

    const handleSaveParking = async () => {
        try {
            if (!selectedParking) {
                await dispatch(createParking(parkingData));
            } else {
                await dispatch(updateParking(selectedParking.id, parkingData));
            }
            onHide();
            refreshData();
            dispatch(getParkings());
        } catch (error) {
            console.error('Error saving city:', error);
        }
    };

    const handleCityChange = (e: ChangeEvent<HTMLSelectElement>) => {
        const { name, value } = e.target;
        setParkingData(prevData => ({ ...prevData, [name]: value }));
    };

    return (
        <Dialog header={selectedParking ? 'Update Parking' : 'Create Parking'} visible={visible}
                style={{width: '450px'}} modal className="p-fluid" onHide={onHide}>
            <div className="p-field">
                <label htmlFor="name" className='block text-gray-700'>Parking Name</label>
                <input id="name" name="name" type="text" value={parkingData.name} onChange={handleChange}
                       className="w-full mt-1 p-2 border rounded-md focus:outline-none focus:ring-blue-500 focus:border-blue-500"/>
            </div>
            <div className="p-field mb-4">
                <label htmlFor="city" className="block text-gray-700">City</label>
                <select
                    id="city"
                    name="city"
                    value={parkingData.city || ''}
                    onChange={(e) => handleCityChange(e)}
                    className="w-full mt-1 p-2 border rounded-md focus:outline-none focus:ring-blue-500 focus:border-blue-500"
                >
                    <option value="">City</option>
                    <option value="Agadir">Agadir</option>
                    <option value="Marrakech">Marrakech</option>
                    <option value="Casablanca">Casablanca</option>
                </select>
            </div>
            <div className="p-field flex justify-center items-center mt-4 mb-4">
                <Button label="Save" onClick={handleSaveParking}
                        className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded mx-3 w-20"/>
                <Button label="Cancel" onClick={onHide}
                        className="bg-gray-500 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded  w-24"/>
            </div>
        </Dialog>
    );
};

export default ParkingDialog;
