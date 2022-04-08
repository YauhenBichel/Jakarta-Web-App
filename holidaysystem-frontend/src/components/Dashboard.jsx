import { ComponentsLayout } from './ComponentsLayout';
import { StatCards } from './StatCards';
import { Table } from './Table';

export function Dashboard() {
	return (
		<ComponentsLayout>
			<div>
				<StatCards />
			</div>
			<div className='py-5'>
				<Table />
			</div>
		</ComponentsLayout>
	);
}
