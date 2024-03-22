import React, { ChangeEvent, useEffect, useState } from 'react';
import { Dialog } from 'primereact/dialog';
import { Button } from 'primereact/button';
import { createParking,updateParking,getParkings} from "../../../redux/actions/parkingActions";
import {useAppDispatch} from "../../../redux/store/store";
import {Parking} from "../../../intrfcs/parking/parking";
import {Floor} from "../../../intrfcs/floor/floor";
import {useSelector} from "react-redux";
import {RootState} from "../../../redux/reducers/RootState";
import {createFloor, getFloors, updateFloor} from "../../../redux/actions/floorActions";

interface FloorDialogProps {
    visible: boolean;
    onHide: () => void;
    refreshData: () => void;
    selectedFloor?: Floor | null;
}

const FloorDialog: React.FC<FloorDialogProps> = ({ visible, onHide, refreshData, selectedFloor }) => {
    const dispatch = useAppDispatch();
    const { parkings } = useSelector((state: RootState) => state.parking);
    const [floorData, setFloorData] = useState<Partial<Floor>>({ nbr: 0 });

    useEffect(() => {

        dispatch(getParkings());

        if (selectedFloor) {
            setFloorData(selectedFloor);
        } else {
            setFloorData({ nbr: 0 });
        }
    }, [selectedFloor]);

    const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setFloorData(prevData => ({ ...prevData, [name]: value }));
    };

    const handleSaveFloor = async () => {
        try {
            if (!selectedFloor) {
                await dispatch(createFloor(floorData));
            } else {
                await dispatch(updateFloor(selectedFloor.id, floorData));
            }
            onHide();
            refreshData();
            dispatch(getFloors());
        } catch (error) {
            console.error('Error saving floor:', error);
        }
    };

    const handleParkingChange = (e: ChangeEvent<HTMLSelectElement>) => {
        const { name, value } = e.target;
        setFloorData(prevData => ({ ...prevData, [name]: value }));
    };


    return (
        <Dialog header={selectedFloor ? 'Update Floor' : 'ADD Floor'} visible={visible}
                style={{width: '450px'}} modal className="p-fluid" onHide={onHide}>
            <div className="p-field">
                <label htmlFor="name" className='block text-gray-700'>Floor Nbr</label>
                <input id="nbr" name="nbr" type="number" value={floorData.nbr} onChange={handleChange}
                       className="w-full mt-1 p-2 border rounded-md focus:outline-none focus:ring-blue-500 focus:border-blue-500"/>
            </div>

            <div className="p-field mb-4">
                <label htmlFor="parking" className="block text-gray-700">Parking</label>
                <select
                    id="parking"
                    name="parking"
                    value={floorData.parking || ''}
                    onChange={(e) => handleParkingChange(e)}
                    className="w-full mt-1 p-2 border rounded-md focus:outline-none focus:ring-blue-500 focus:border-blue-500"
                >
                    <option value="">Select a parking</option>
                    {parkings.map(parking => (
                        <option key={parking.id} value={parking.id}>{parking.name}</option>
                    ))}
                </select>
            </div>

            <div className="p-field flex justify-center items-center mt-4 mb-4">
                <Button label="Save" onClick={handleSaveFloor}
                        className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded mx-3 w-20"/>
                <Button label="Cancel" onClick={onHide}
                        className="bg-gray-500 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded  w-24"/>
            </div>
        </Dialog>
    );
};

export default FloorDialog;
