import { useEffect } from 'react';
import { Card } from 'primereact/card';
import "primereact/resources/themes/lara-light-cyan/theme.css";
import 'primereact/resources/primereact.min.css';
import 'primeicons/primeicons.css';
import { useSelector } from 'react-redux';
import { RootState } from '../../redux/reducers/RootState';
import {useAppDispatch} from "../../redux/store/store";
import {getParkings} from "../../redux/actions/parkingActions";
import parking_ from '../../assets/imgs/parking/parking_.jpg'


function ParkingsList() {
    const {parkings, loading, error } = useSelector((state: RootState) => state.parking);
    const dispatch = useAppDispatch();


    useEffect(() => {
        dispatch(getParkings());
    }, [dispatch]);




    const getRandomImage = () => {
        const randomIndex = Math.floor(Math.random() * 10) + 1;
        return `https://source.unsplash.com/featured/300x201/?${randomIndex}`;
    };

    return (

        <div>
            <div className="container flex flex-col items-center px-6 py-12 mx-auto lg:flex-row">
                <div className="lg:w-1/2">
                    <h1 className="max-w-xl font-serif text-4xl font-medium tracking-wide text-[#343D33] capitalize md:text-6xl ">A beatiful adventure awaits</h1>

                    <p className="max-w-lg mt-4 text-gray-500">Lorem ipsum dolor sit amet consectetur adipisicing elit. At magnam voluptatibus perferendis odit optio.</p>

                    <div className="mt-6 sm:flex sm:items-center">
                        <a href="#" className="bg-[#475F45] hover:bg-[#475F45]/80 duration-300 transition-colors border-2 border-[#475F45] px-6 block text-center py-3 uppercase text-sm font-bold leading-4 tracking-widest text-white ">
                            Book Your Place
                        </a>

                        <a href="#" className="border-2 text-sm duration-300 transition-colors hover:bg-[#475F45] hover:text-white font-bold leading-4 mt-4 sm:mt-0 tracking-widest text-[#475F45] sm:mx-4 border-[#475F45] px-6 block text-center py-3 uppercase">
                            Learn More
                        </a>
                    </div>
                </div>

                <div className="h-[38rem] mt-12 lg:mt-0 w-full mx-auto max-w-md overflow-hidden rounded-t-full outline outline-4 outline-offset-4 outline-[#475F45]/40">
                    <img className="object-cover w-full h-full rounded-t-full " src={parking_} alt="main page" />
                </div>
            </div>


            <h3 className="font-serif text-3xl text-[#343D33] capitalize md:text-4xl lg:text-5xl text-center mt-6 mb-6">Parkings</h3>


            <div className="card flex justify-content-center flex-wrap w-auto" style={{ border: 'none' }}>

                {loading ? 'Loading...' : (error ? error :
                        parkings.map((parking, index) => (
                            <Card key={index} title={parking.name}
                                  header={<img alt="Card" src={getRandomImage()} />} className="md:w-25rem w-96 mx-4 mb-8">
                                <p className="m-0">
                                    {parking.city}
                                </p>
                            </Card>
                        ))
                )}
            </div>
        </div>
    );
}

export default ParkingsList;