import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import { NavBar } from './NavBar';
import { Dashboard } from './Dashboard';
import { RequestExample } from './RequestExample';
import { AllEmployees } from './AllEmployees';
import { MyRequests } from './MyRequests';
import { NewRequest } from './NewRequest';
import { ProfileInformation } from './ProfileInformation';
import { AllRequestsTable } from './AllRequestsTable';
import { AddEmployee } from './AddEmployee';

export function AuthenticatedApp() {
	return (
		<div className=''>
			<Router>
				<NavBar />
				<Routes>
					<Route path='/' element={<Dashboard />} />
					<Route path='/my-requests' element={<MyRequests />} />
					<Route path='/all-requests' element={<AllRequestsTable />} />
					<Route path='/all-employees' element={<AllEmployees />} />
					<Route path='/request-example' element={<RequestExample />} />
					<Route path='/new-request' element={<NewRequest />} />
					<Route path='/profile' element={<ProfileInformation />} />
					<Route path='/add-employee' element={<AddEmployee />} />
				</Routes>
			</Router>
		</div>
	);
}
