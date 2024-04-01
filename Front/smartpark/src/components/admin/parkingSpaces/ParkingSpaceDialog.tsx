import {ParkingSpace} from "../../../intrfcs/parkingSpace/parkingspace";
import {useAppDispatch} from "../../../redux/store/store";
import {useSelector} from "react-redux";
import {RootState} from "../../../redux/reducers/RootState";
import {ChangeEvent, useEffect, useState} from "react";
import {getFloors} from "../../../redux/actions/floorActions";
import {createParkingSpace, getParkingSpaces, updateParkingSpace} from "../../../redux/actions/parkingSpaceActions";
import {Dialog} from "primereact/dialog";
import {Button} from "primereact/button";

interface ParkingSpaceDialogProps {
    visible: boolean;
    onHide: () => void;
    refreshData: () => void;
    selectedParkingSpace?: ParkingSpace | null;
}

const ParkingSpaceDialog: React.FC<ParkingSpaceDialogProps> = ({ visible, onHide, refreshData, selectedParkingSpace }) => {
    const dispatch = useAppDispatch();
    const { floors } = useSelector((state: RootState) => state.floor);
    const [parkingSpaceData, setParkingSpaceData] = useState<Partial<ParkingSpace>>({ nbr: '' });

    useEffect(() => {

        dispatch(getFloors());

        if (selectedParkingSpace) {
            setParkingSpaceData(selectedParkingSpace);
        } else {
            setParkingSpaceData({ nbr: '' });
        }
    }, [selectedParkingSpace]);

    const handleChange = (e: ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setParkingSpaceData(prevData => ({ ...prevData, [name]: value }));
    };

    const handleSaveParkingSpace = async () => {
        try {
            if (!selectedParkingSpace) {
                await dispatch(createParkingSpace(parkingSpaceData));
            } else {
                await dispatch(updateParkingSpace(selectedParkingSpace.id, parkingSpaceData));
            }
            onHide();
            refreshData();
            dispatch(getParkingSpaces());
        } catch (error) {
            console.error('Error saving parking space:', error);
        }
    };

    const handleFloorChange = (e: ChangeEvent<HTMLSelectElement>) => {
        const { name, value } = e.target;
        setParkingSpaceData(prevData => ({ ...prevData, [name]: value }));
    };


    return (
        <Dialog header={selectedParkingSpace ? 'Update parking space' : 'ADD parking space'} visible={visible}
                style={{width: '450px'}} modal className="p-fluid" onHide={onHide}>
            <div className="p-field">
                <label htmlFor="nbr" className='block text-gray-700'>Parking Space Nbr</label>
                <input id="nbr" name="nbr" type="text" value={parkingSpaceData.nbr} onChange={handleChange}
                       className="w-full mt-1 p-2 border rounded-md focus:outline-none focus:ring-blue-500 focus:border-blue-500"/>
            </div>

            <div className="p-field mb-4">
                <label htmlFor="floor" className="block text-gray-700">Floor</label>
                <select
                    id="floor"
                    name="floor"
                    value={parkingSpaceData.floor || ''}
                    onChange={(e) => handleFloorChange(e)}
                    className="w-full mt-1 p-2 border rounded-md focus:outline-none focus:ring-blue-500 focus:border-blue-500"
                >
                    <option value="">Select a parking</option>
                    {floors.map(floor => (
                        <option key={floor.id} value={floor.id}>{floor.parkingName}-{floor.nbr}</option>
                    ))}
                </select>
            </div>

            <div className="p-field flex justify-center items-center mt-4 mb-4">
                <Button label="Save" onClick={handleSaveParkingSpace}
                        className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded mx-3 w-20"/>
                <Button label="Cancel" onClick={onHide}
                        className="bg-gray-500 hover:bg-gray-700 text-white font-bold py-2 px-4 rounded  w-24"/>
            </div>
        </Dialog>
    );
};

export default ParkingSpaceDialog;
