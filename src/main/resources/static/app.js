'use strict';

const React = require('react');
const when = require('when');
const client = require('./client');
const follow = require('./follow'); // function to hop multiple links by "rel"
const stompClient = require('./websocket-listener');
const isAnAdmin = document.currentScript.getAttribute('admin');
const root = '/api';


class User extends React.Component {

    constructor(props) {
        super(props);
        this.handleDelete = this.handleDelete.bind(this);
    }

    handleDelete() {
        this.props.onDelete(this.props.user);
    }

    render() {

        return (
            <tr>
                <td>{this.props.user.entity.securityNumber}</td>
                <td>{this.props.user.entity.username}</td>
                <td>{this.props.user.entity.firstName}</td>
                <td>{this.props.user.entity.lastName}</td>
                <td>{this.props.user.entity.phone}</td>
                <td>{this.props.user.entity.address}</td>
                <td>{this.props.user.entity.admin + ""}</td>
        
                {function(){
                    if (isAnAdmin) {
                      return
                            <td>
                                <UpdateDialog   user={this.props.user}
                                                attributes={this.props.attributes}
                                                onUpdate={this.props.onUpdate}/>
                            </td>;
                    }else{
                        return <td></td>;
                    }
                }.call(this)}
                {function(){
                    if (isAnAdmin) {
                      return
                        <td>
                            <button onClick={this.handleDelete}>Delete</button>
                        </td>;
                    }else{
                        return <td></td>;
                    }
                }.call(this)}
                    
            </tr>
        )
    }
}

class UserList extends React.Component {
    
    constructor(props) {
        super(props);
        this.handleNavFirst = this.handleNavFirst.bind(this);
        this.handleNavPrev = this.handleNavPrev.bind(this);
        this.handleNavNext = this.handleNavNext.bind(this);
        this.handleNavLast = this.handleNavLast.bind(this);
        this.handleInput = this.handleInput.bind(this);
    }

    handleInput(e) {
        e.preventDefault();
        var pageSize = React.findDOMNode(this.refs.pageSize).value;
        if (/^[0-9]+$/.test(pageSize)) {
            this.props.updatePageSize(pageSize);
        } else {
            React.findDOMNode(this.refs.pageSize).value = pageSize.substring(0, pageSize.length - 1);
        }
    }

    handleNavFirst(e) {
        e.preventDefault();
        this.props.onNavigate(this.props.links.first.href);
    }

    handleNavPrev(e) {
        e.preventDefault();
        this.props.onNavigate(this.props.links.prev.href);
    }

    handleNavNext(e) {
        e.preventDefault();
        this.props.onNavigate(this.props.links.next.href);
    }

    handleNavLast(e) {
        e.preventDefault();
        this.props.onNavigate(this.props.links.last.href);
    }

    render() {
        var pageInfo = this.props.page.hasOwnProperty("number") ?
                <h3>Users - Page {this.props.page.number + 1} of {this.props.page.totalPages}</h3> : null;

        var users = this.props.users.map(user =>
            <User   key={user.entity._links.self.href} user={user}
                    attributes={this.props.attributes} onUpdate={this.props.onUpdate}
                    onDelete={this.props.onDelete}/>
        );

        var navLinks = [];
        if ("first" in this.props.links) {
                navLinks.push(<button key="first" onClick={this.handleNavFirst}>&lt;&lt;</button>);
        }
        if ("prev" in this.props.links) {
                navLinks.push(<button key="prev" onClick={this.handleNavPrev}>&lt;</button>);
        }
        if ("next" in this.props.links) {
                navLinks.push(<button key="next" onClick={this.handleNavNext}>&gt;</button>);
        }
        if ("last" in this.props.links) {
                navLinks.push(<button key="last" onClick={this.handleNavLast}>&gt;&gt;</button>);
        }

        return (
            <div>
                {pageInfo}
                <table>
                    <tr>
                        <th>Security Number</th>
                        <th>Username</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Phone</th>
                        <th>Address</th>
                        <th>isAdmin</th>
                        <th>Update</th>
                        <th>Delete</th>
                    </tr>
                    {users}
                </table>
                <div>
                    {navLinks}
                </div>
            </div>
        )
    }
}

class CreateDialog extends React.Component {

    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(e) {
        e.preventDefault();
        var newUser = {};
        this.props.attributes.forEach(attribute => {
                newUSer[attribute] = React.findDOMNode(this.refs[attribute]).value.trim();
        });
        this.props.onCreate(newUser);
        this.props.attributes.forEach(attribute => {
                React.findDOMNode(this.refs[attribute]).value = '';
        });
        window.location = "#";
    }

    render() {
        var inputs = this.props.attributes.map(attribute =>
            <p key={attribute}>
                <input type="text" placeholder={attribute} ref={attribute} className="field" />
            </p>);
        return (
            <div>
                <a href="#createUser">Create</a>

                <div id="createUser" className="modalDialog">
                        <div>
                            <a href="#" title="Close" className="close">X</a>

                            <h2>Create new user</h2>

                            <form>
                                {inputs}
                                <button onClick={this.handleSubmit}>Create</button>
                            </form>
                        </div>
                </div>
            </div>
        )
    }
}

class UpdateDialog extends React.Component {

	constructor(props) {
		super(props);
		this.handleSubmit = this.handleSubmit.bind(this);
	}

	handleSubmit(e) {
		e.preventDefault();
		var updatedUser = {};
		this.props.attributes.forEach(attribute => {
			updatedUser[attribute] = React.findDOMNode(this.refs[attribute]).value.trim();
		});
		this.props.onUpdate(this.props.user, updatedUser);
		window.location = "#";
	}

	render() {
		var inputs = this.props.attributes.map(attribute =>
				<p key={this.props.user.entity[attribute]}>
					<input type="text" placeholder={attribute}
						   defaultValue={this.props.user.entity[attribute]}
						   ref={attribute} className="field" />
				</p>
		);

		var dialogId = "updateUser-" + this.props.user.entity._links.self.href;

		return (
			<div>
				<a href={"#" + dialogId}>Update</a>

				<div id={dialogId} className="modalDialog">
					<div>
						<a href="#" title="Close" className="close">X</a>

						<h2>Update a user</h2>

						<form>
							{inputs}
							<button onClick={this.handleSubmit}>Update</button>
						</form>
					</div>
				</div>
			</div>
		)
	}

}

class App extends React.Component {

	constructor(props) {
                console.log("===========" + isAnAdmin);
                
		super(props);
		this.state = {users: [], attributes: [], page: 1, pageSize: 10, links: {}};
		this.updatePageSize = this.updatePageSize.bind(this);
		this.onCreate = this.onCreate.bind(this);
		this.onUpdate = this.onUpdate.bind(this);
		this.onDelete = this.onDelete.bind(this);
		this.onNavigate = this.onNavigate.bind(this);
		this.refreshCurrentPage = this.refreshCurrentPage.bind(this);
		this.refreshAndGoToLastPage = this.refreshAndGoToLastPage.bind(this);
	}

	loadFromServer(pageSize) {
		follow(client, root, [
				{rel: 'users'}]
		).then(userCollection => {
			return client({
				method: 'GET',
				path: userCollection.entity._links.profile.href,
				headers: {'Accept': 'application/schema+json'}
			}).then(schema => {
				Object.keys(schema.entity.properties).forEach(function (property) {
					if (schema.entity.properties[property].hasOwnProperty('format') &&
						schema.entity.properties[property].format === 'uri') {
						delete schema.entity.properties[property];
					}
					if (schema.entity.properties[property].hasOwnProperty('$ref')) {
						delete schema.entity.properties[property];
					}
				});

				this.schema = schema.entity;
				this.links = userCollection.entity._links;
				return userCollection;
				// end::json-schema-filter[]
			});
		}).then(userCollection => {
			this.page = userCollection.entity.page;
			return userCollection.entity._embedded.users.map(user =>
					client({
						method: 'GET',
						path: user._links.self.href
					})
			);
		}).then(userPromises => {
			return when.all(userPromises);
		}).done(users => {
			this.setState({
				page: this.page,
				users: users,
				attributes: Object.keys(this.schema.properties),
				pageSize: pageSize,
				links: this.links
			});
		});
	}

	// tag::on-create[]
	onCreate(newUser) {
		follow(client, root, ['users']).done(response => {
			client({
				method: 'POST',
				path: response.entity._links.self.href,
				entity: newUser,
				headers: {'Content-Type': 'application/json'}
			})
		})
	}
	// end::on-create[]

	// tag::on-update[]
	onUpdate(user, updateUser) {
		client({
			method: 'PUT',
			path: user.entity._links.self.href,
			entity: updatedUser,
			headers: {
				'Content-Type': 'application/json',
				'If-Match': user.headers.Etag
			}
		}).done(response => {
			/* Let the websocket handler update the state */
		}, response => {
			if (response.status.code === 403) {
				alert('ACCESS DENIED: You are not authorized to update ' +
					user.entity._links.self.href);
			}
			if (response.status.code === 412) {
				alert('DENIED: Unable to update ' + user.entity._links.self.href +
					'. Your copy is stale.');
			}
		});
	}
	// end::on-update[]

	// tag::on-delete[]
	onDelete(user) {
		client({method: 'DELETE', path: user.entity._links.self.href}
		).done(response => {/* let the websocket handle updating the UI */},
		response => {
			if (response.status.code === 403) {
				alert('ACCESS DENIED: You are not authorized to delete ' +
					user.entity._links.self.href);
			}
		});
	}
	// end::on-delete[]

	onNavigate(navUri) {
		client({
			method: 'GET',
			path: navUri
		}).then(userCollection => {
			this.links = userCollection.entity._links;
			this.page = userCollection.entity.page;

			return userCollection.entity._embedded.users.map(user =>
                            client({
                                    method: 'GET',
                                    path: user._links.self.href
                            })
			);
		}).then(userPromises => {
			return when.all(userPromises);
		}).done(users => {
			this.setState({
				page: this.page,
				users: users,
				attributes: Object.keys(this.schema.properties),
				pageSize: this.state.pageSize,
				links: this.links
			});
		});
	}

	updatePageSize(pageSize) {
		if (pageSize !== this.state.pageSize) {
			this.loadFromServer(pageSize);
		}
	}

	// tag::websocket-handlers[]
	refreshAndGoToLastPage(message) {
		follow(client, root, [{
			rel: 'users',
			params: {size: this.state.pageSize}
		}]).done(response => {
			if (response.entity._links.last !== undefined) {
				this.onNavigate(response.entity._links.last.href);
			} else {
				this.onNavigate(response.entity._links.self.href);
			}
		})
	}

	refreshCurrentPage(message) {
		follow(client, root, [{
			rel: 'users',
			params: {
				size: this.state.pageSize,
				page: this.state.page.number
			}
		}]).then(userCollection => {
			this.links = userCollection.entity._links;
			this.page = userCollection.entity.page;

			return userCollection.entity._embedded.users.map(user => {
				return client({
					method: 'GET',
					path: user._links.self.href
				})
			});
		}).then(userPromises => {
			return when.all(userPromises);
		}).then(users => {
			this.setState({
				page: this.page,
				users: users,
				attributes: Object.keys(this.schema.properties),
				pageSize: this.state.pageSize,
				links: this.links
			});
		});
	}

	componentDidMount() {
		this.loadFromServer(this.state.pageSize);
		stompClient.register([
			{route: '/topic/newUser', callback: this.refreshAndGoToLastPage},
			{route: '/topic/updateUser', callback: this.refreshCurrentPage},
			{route: '/topic/deleteUser', callback: this.refreshCurrentPage}
		]);
	}

	render() {
		return (
			<div>
				<CreateDialog attributes={this.state.attributes} onCreate={this.onCreate}/>
				<UserList   page={this.state.page}
                                            users={this.state.users}
                                            links={this.state.links}
                                            pageSize={this.state.pageSize}
                                            attributes={this.state.attributes}
                                            onNavigate={this.onNavigate}
                                            onUpdate={this.onUpdate}
                                            onDelete={this.onDelete}
                                            updatePageSize={this.updatePageSize}/>
			</div>
		)
	}
}



React.render(<App/>, document.getElementById('react'))

