import { Link } from 'react-router-dom';
import { ComponentsLayout } from './ComponentsLayout';
import { MyRequestsTable } from './MyRequestsTable';
import { FilterButton } from './FilterButton';

export function MyRequests() {
	return (
		<ComponentsLayout>
			<div className='flex items-center justify-between'>
				<h3 className='text-lg leading-6 font-bold text-gray-900'>
					My Holiday Requests
				</h3>
				<Link
					to='/new-request'
					className='relative inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md text-white bg-blue-600 shadow-sm hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500'>
					New Request
				</Link>
			</div>
			<MyRequestsTable />
		</ComponentsLayout>
	);
}
